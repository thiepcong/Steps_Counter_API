package com.example.stepcounter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.model.DeviceAccount;

@Repository
public interface DeviceAccountRepository extends JpaRepository<DeviceAccount, Integer>{
	public DeviceAccount getById(String deviceId);
}
