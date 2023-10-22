package com.example.stepcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@GetMapping("/week")
	public @ResponseBody UserStatOutputDto getStatWeekByUser(@RequestHeader("token") String token){

		User user = userServiceImpl.getUser(token);
		List<StepCounterOuputDto> stepCounterOuputDtos = stepCounterService.getStatWeekByUser(user.getId());
		
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
	
	@GetMapping("/month")
	public @ResponseBody UserStatOutputDto getStatMonthByUser(@RequestHeader("token") String token, Integer numberDay){
		
		numberDay = stepCounterService.getDateOfMonth();
		User user = userServiceImpl.getUser(token);
		List<StepCounterOuputDto> stepCounterOuputDtos = stepCounterService.getStatMonthByUser(user.getId(), numberDay);
		
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
