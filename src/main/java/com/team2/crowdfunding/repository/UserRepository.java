package com.team2.crowdfunding.repository;

import com.team2.crowdfunding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}