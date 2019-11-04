package cs544.cs.mum.edu.batchupload.repository;

import cs544.cs.mum.edu.batchupload.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
