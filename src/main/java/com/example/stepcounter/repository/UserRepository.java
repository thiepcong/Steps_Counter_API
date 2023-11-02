package com.example.stepcounter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User getUserByToken(String token);
}
