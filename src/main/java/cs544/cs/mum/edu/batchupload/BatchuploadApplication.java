package cs544.cs.mum.edu.batchupload;

import cs544.cs.mum.edu.batchupload.model.Role;
import cs544.cs.mum.edu.batchupload.model.User;
import cs544.cs.mum.edu.batchupload.repository.RoleRepository;
import cs544.cs.mum.edu.batchupload.serviceImpl.RoleServiceImpl;
import cs544.cs.mum.edu.batchupload.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchuploadApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    public static void main(String[] args) {
        SpringApplication.run(BatchuploadApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Role role = new Role("ROLE_ADMIN");
        Role addedRole = initializeRole(role);

        System.out.println("role added: "+ addedRole);

        User user  = new User("Chinedu", "chinedu");
        user.setRole(addedRole);
        User addedUser = initializeUser(user);

        System.out.println("user added: " + addedUser);

    }

    private User initializeUser(User user) {
        return userService.saveUser(user);
    }

    private Role initializeRole(Role role) {
        return roleService.saveRole(role);
    }
}
