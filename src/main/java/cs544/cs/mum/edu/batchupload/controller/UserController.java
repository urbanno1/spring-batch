package cs544.cs.mum.edu.batchupload.controller;

import cs544.cs.mum.edu.batchupload.model.User;
import cs544.cs.mum.edu.batchupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/adduser")
    public String addUser(@RequestBody User user) {
        User userAdded = userService.saveUser(user);
        if(userAdded != null) return "User added successfully";
        else return "Request Failed!!";
    }
}
