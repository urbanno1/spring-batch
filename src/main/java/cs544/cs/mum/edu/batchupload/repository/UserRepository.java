package cs544.cs.mum.edu.batchupload.repository;

import cs544.cs.mum.edu.batchupload.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
