package com.example.stepcounter.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.model.User;
import com.example.stepcounter.repository.UserRepository;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserMapper mapper;
	
	@Autowired
	private UserRepository userRepository;

	public UserOutputDto getUser(String token) {
		return mapper.getOutputFromEntity(userRepo.getUserByToken(token));
	}

	public boolean refreshToken(String token) {
		User user = userRepo.getUserByToken(token);
		if (user != null) {
			user.setToken(UUID.randomUUID().toString());
			userRepo.save(user);
			return true;
		}
		return false;
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id);
	}
	
	public List<UserChartOutputDto> getChartAll() {
		return userRepository.getChartAll();
	}
}
