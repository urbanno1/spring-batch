package cs544.cs.mum.edu.batchupload.config;

import cs544.cs.mum.edu.batchupload.batch.BatchProcessor;
import cs544.cs.mum.edu.batchupload.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Student> itemReader(){
        FlatFileItemReader<Student> itemReader = new FlatFileItemReader<Student>();
        itemReader.setResource(new ClassPathResource("student.csv"));
        itemReader.setLinesToSkip(1);

        itemReader.setLineMapper(new DefaultLineMapper<Student>(){
            {
            setLineTokenizer(new DelimitedLineTokenizer(){
                {
             setNames(new String[] {"firstName", "lastName", "gpa", "birthAge"});
             setDelimiter(",");
             setStrict(false);

             setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>(){
                 {
                 setTargetType(Student.class);
             }});
            }});
        }});
        return  itemReader;
    }

    @Bean
    public BatchProcessor itemProcessor() {
        return  new BatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Student> itemWriter() {
        JdbcBatchItemWriter<Student> itemWriter = new JdbcBatchItemWriter<Student>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Student>());
        itemWriter.setSql("INSERT INTO students (first_name, last_name, gpa, birth_age, birth_date) " +
                "VALUES (:firstName, :lastName, :gpa, :birthAge, :birthDate) ");
        itemWriter.setDataSource(dataSource);
        return  itemWriter;
    }

    @Bean
    public Step step1() {
        return  stepBuilderFactory.get("step1").<Student, Student>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("CSV-load")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
}
