package com.example.stepcounter.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.enums.ErrorCode;
import com.example.stepcounter.exceptions.CommandException;
import com.example.stepcounter.model.User;
import com.example.stepcounter.repository.UserRepository;
import com.example.stepcounter.service.stepcounter.StepCounterService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StepCounterService stepCounterService;
	
	public UserOutputDto getUser(String token) {
		User user = userRepo.getUserByToken(token);
		
		if(user != null) {
			UserOutputDto userOutputDto = mapper.getOutputFromEntity(user);
			userOutputDto.setTodayStepCounter(stepCounterService.getTodayStepCounter(user));
			return userOutputDto;
		}
		else throw new CommandException(ErrorCode.ACCOUNT_NOT_FOUND);
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
		
	public User updateUser(String token, UserOutputDto user, User updatedUser) {	
		User updateInfo = new User();
		updateInfo.setId(user.getId());
		updateInfo.setName(updatedUser.getName());
		updateInfo.setAge(updatedUser.getAge());
		updateInfo.setGender(updatedUser.getGender());
		updateInfo.setHeight(updatedUser.getHeight());
		updateInfo.setWeight(updatedUser.getWeight());
		updateInfo.setToken(token);
		return userRepository.save(updateInfo);
    }
	
	public List<UserChartOutputDto> getChartAll() {
		return userRepository.getChartAll();
	}
}
