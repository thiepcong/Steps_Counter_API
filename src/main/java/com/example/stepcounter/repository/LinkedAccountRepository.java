package com.example.stepcounter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.model.LinkedAccount;

@Repository
public interface LinkedAccountRepository extends JpaRepository<LinkedAccount, Integer> {
	public LinkedAccount getById(String id);
}
