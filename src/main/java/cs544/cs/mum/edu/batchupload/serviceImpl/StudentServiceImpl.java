package cs544.cs.mum.edu.batchupload.serviceImpl;

import cs544.cs.mum.edu.batchupload.model.Student;
import cs544.cs.mum.edu.batchupload.repository.StudentRepository;
import cs544.cs.mum.edu.batchupload.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        LocalDate newDate = processBirthDate(student.getBirthAge());
        student.setBirthDate(newDate);

        return studentRepository.save(student);
    }

    private LocalDate processBirthDate(Integer birthAge) {
        Integer age = birthAge;
        Integer year = LocalDate.now().getYear() - age;

        LocalDate newDate = LocalDate.of(year, 1, 1);
        return newDate;
    }
}
