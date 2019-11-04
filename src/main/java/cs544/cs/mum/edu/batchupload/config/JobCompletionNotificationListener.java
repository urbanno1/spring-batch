package cs544.cs.mum.edu.batchupload.config;

import cs544.cs.mum.edu.batchupload.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private  final JdbcTemplate jdbcTemplate;

          public  JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
          }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED !! It's time to verify the results!!");

            List<Student> results = jdbcTemplate.query(
                    "SELECT first_name, last_name FROM students", new RowMapper<Student>() {

                        @Override
                        public Student mapRow(ResultSet rs, int row) throws SQLException {
                            return new Student(rs.getString(1), rs.getString(2));
                        }
                    });

            for (Student student : results) {
                log.info("Found <" + student + "> in the database.");
            }
        }
    }
}
