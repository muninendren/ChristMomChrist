package com.Christmas.ChirstMomChristChild.Service;

import com.Christmas.ChirstMomChristChild.Entity.User;
import com.Christmas.ChirstMomChristChild.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<String> getAllUnregisteredUser(){

        List<User> users =  repository.findAll();
        List<String> unregisteredUsers= users.stream()
                .filter(a -> a.getPassword()==null)
                        .map(User::getName)
                                .toList();
        System.out.println("test");

        return unregisteredUsers;
    }

    public List<String> getAllregisteredUser(){

        List<User> users =  repository.findAll();
        List<String> unregisteredUsers= users.stream()
                .filter(a -> a.getPassword()!=null)
                .map(User::getName)
                .toList();
        System.out.println("test");

        return unregisteredUsers;
    }

    public void registerUser(User user){
        User user1= repository.findById(user.getName()).get();
        user1.setPassword(user.getPassword());
        repository.save(user1);
    }


}
