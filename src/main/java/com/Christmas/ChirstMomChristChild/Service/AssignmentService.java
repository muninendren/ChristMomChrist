package com.Christmas.ChirstMomChristChild.Service;

import com.Christmas.ChirstMomChristChild.Entity.Assignment;
import com.Christmas.ChirstMomChristChild.Entity.User;
import com.Christmas.ChirstMomChristChild.Repository.AssignmentRepository;
import com.Christmas.ChirstMomChristChild.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    UserRepository repository;

    @Autowired
    AssignmentRepository assignmentRepository;


    public void assignment() {

        Random random = new Random();

        List<User> users = repository.findAll();
        List<String> moms = users.stream()
                .map(User::getName)
                .toList();
        if(users.size()<2){
            return;
        }

        List<String> children = new ArrayList<>(moms);

        // Shuffle initially
        Collections.shuffle(children);

        // Fix if someone gets themselves
        for (int i = 0; i < moms.size(); i++) {
            if (moms.get(i).equals(children.get(i))) {
                // simple fix: swap two children
                int swapWith = (i + 1) % children.size();
                String temp = children.get(i);
                children.set(i, children.get(swapWith));
                children.set(swapWith, temp);
            }
        }

        // Save assignments
        List<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < moms.size(); i++) {
            assignments.add(new Assignment(moms.get(i), children.get(i)));
        }

        assignments.forEach(System.out::println);

        assignmentRepository.saveAll(assignments);
    }


    public Assignment getUrChild(User user){
        Optional<User> userOptional = repository.findById(user.getName());
        if(userOptional.get().getPassword() == null){
            return new Assignment(user.getName(), "First neenga register pannunga ponga");
        }
        if(!userOptional.get().getPassword().equals(user.getPassword())){
            return new Assignment(user.getName(), "oops password thappu pa");
        }
        Optional<Assignment> assignment = assignmentRepository.findById(user.getName());
        if(assignment.isEmpty() || assignment.get().getChristChild()==null ){
            return new Assignment(user.getName(), "inno child assign pannala- sorry");
        }else {
            return assignment.get();
        }
    }




    public void assignmentt(){

        Random random = new Random();

        List<User> users =  repository.findAll();
        List<String> usersMom= users.stream()
                .map(User::getName)
                .toList();

        List<String> usersChild = new ArrayList<>(usersMom);

        List<Assignment> assignments = new ArrayList<>();
        for(String mom: usersMom){
            int index= usersChild.size()-1 % random.nextInt(usersChild.size()-1);

            Assignment assignment = new Assignment(mom , usersChild.get(index));
            assignments.add(assignment);
            usersChild.remove(index);
        }

        for(Assignment a: assignments){
            System.out.println(a);
        }

    }
}
