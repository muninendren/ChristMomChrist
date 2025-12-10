package com.Christmas.ChirstMomChristChild.Controller;

import com.Christmas.ChirstMomChristChild.Entity.Assignment;
import com.Christmas.ChirstMomChristChild.Entity.User;
import com.Christmas.ChirstMomChristChild.Service.AssignmentService;
import com.Christmas.ChirstMomChristChild.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/christmomchrist")
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    AssignmentService assignmentService;

    @GetMapping("/unregisteredUsers")
    public List<String> getAllUnregisteredUsers(){
        return userService.getAllUnregisteredUser();
    }

    @GetMapping("/registeredUsers")
    public List<String> getAllregisteredUsers(){
        return userService.getAllregisteredUser();
    }

    @PostMapping("/register")
    public void register(@RequestBody  User user){
        userService.registerUser(user);
    }

    @PostMapping("/getChild")
    public Assignment geturChild(@RequestBody  User user){
        return assignmentService.getUrChild(user);
    }

    @PostMapping("/Assign")
    public void assign(){
        assignmentService.assignment();
    }
}
