package cs544.cs.mum.edu.batchupload.controller;

import cs544.cs.mum.edu.batchupload.model.Student;
import cs544.cs.mum.edu.batchupload.service.StudentService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @RequestMapping("/load")
    private String load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameter = new JobParameters(maps);

        jobLauncher.run(job, jobParameter);
        return "Job Completed";
    }

    @RequestMapping("/home")
    public String home() {
        return "Welcome home to a restful application";
    }

    @RequestMapping("/addstudent")
    public String addStudent(@RequestBody Student student) {
        Student studentAdded = studentService.saveStudent(student);
        if(studentAdded != null) return "Student successfully added";
        else return "Failed to add Student!";
    }

}
