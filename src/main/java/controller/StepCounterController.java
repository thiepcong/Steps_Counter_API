package com.example.stepcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.dto.stepcounter.StepCounterOuputDto;
import com.example.stepcounter.dto.user.UserStatOutputDto;
import com.example.stepcounter.modal.User;
import com.example.stepcounter.service.stepcounter.StepCounterService;
import com.example.stepcounter.service.user.UserServiceImpl;


@RestController
@RequestMapping("/api/stat")
public class StepCounterController {
	@Autowired
	private StepCounterService stepCounterService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
//Báo cáo thống kê
	@GetMapping("/week/{userId}")
	public @ResponseBody UserStatOutputDto getStatWeekByUser(@PathVariable("userId") Integer userId){

		User user = userServiceImpl.getUserById(userId);
		List<StepCounterOuputDto> stepCounterOuputDtos = stepCounterService.getStatWeekByUser(userId);
		
		UserStatOutputDto userStatOutputDto = new UserStatOutputDto();
		userStatOutputDto.setId(user.getId());
		userStatOutputDto.setName(user.getName());
		userStatOutputDto.setAge(user.getAge());
		userStatOutputDto.setHeight(user.getHeight());
		userStatOutputDto.setWeight(user.getWeight());
		userStatOutputDto.setGender(user.getGender());
		userStatOutputDto.setStepCounterOuputDtos(stepCounterOuputDtos);
		return userStatOutputDto;
	}
	
	@GetMapping("/month/{userId}")
	public @ResponseBody UserStatOutputDto getStatMonthByUser(@PathVariable("userId") Integer userId, Integer numberDay){
		
		numberDay = stepCounterService.getDateOfMonth();
		User user = userServiceImpl.getUserById(userId);
		List<StepCounterOuputDto> stepCounterOuputDtos = stepCounterService.getStatMonthByUser(userId, numberDay);
		
		UserStatOutputDto userStatOutputDto = new UserStatOutputDto();
		userStatOutputDto.setId(user.getId());
		userStatOutputDto.setName(user.getName());
		userStatOutputDto.setAge(user.getAge());
		userStatOutputDto.setHeight(user.getHeight());
		userStatOutputDto.setWeight(user.getWeight());
		userStatOutputDto.setGender(user.getGender());
		userStatOutputDto.setStepCounterOuputDtos(stepCounterOuputDtos);
		return userStatOutputDto;
	}
}
