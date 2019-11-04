package cs544.cs.mum.edu.batchupload.serviceImpl;

import cs544.cs.mum.edu.batchupload.model.User;
import cs544.cs.mum.edu.batchupload.repository.UserRepository;
import cs544.cs.mum.edu.batchupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }
}
