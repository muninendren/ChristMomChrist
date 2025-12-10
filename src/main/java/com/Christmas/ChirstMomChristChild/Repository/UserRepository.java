package com.Christmas.ChirstMomChristChild.Repository;

import com.Christmas.ChirstMomChristChild.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
