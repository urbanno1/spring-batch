package cs544.cs.mum.edu.batchupload.batch;

import cs544.cs.mum.edu.batchupload.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class BatchProcessor implements ItemProcessor<Student, Student> {
    private static final Logger log = LoggerFactory.getLogger(BatchProcessor.class);

    @Override
    public Student process(Student student) throws Exception {
        LocalDate birthdate = processBirthDate(student.getBirthAge());
        log.info("converting the age: " + student.getBirthAge() + "to birth year: " + birthdate);
        student.setBirthDate(birthdate);
        return student;
    }

    private LocalDate processBirthDate(Integer birthAge) {
        Integer age = birthAge;
        Integer year = LocalDate.now().getYear() - age;

        LocalDate newDate = LocalDate.of(year, 1, 1);
        return newDate;
    }
}
