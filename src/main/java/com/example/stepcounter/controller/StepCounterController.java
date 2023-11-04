package com.example.stepcounter.controller;

import java.util.List;

import com.example.stepcounter.dto.stepcounter.StepCounterInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.stepcounter.dto.stepcounter.StepCounterOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.dto.user.UserStatOutputDto;
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

		UserOutputDto user = userServiceImpl.getUser(token);
		List<StepCounterOutputDto> stepCounterOutputDtos = stepCounterService.getStatWeekByUser(user.getId());
		
		UserStatOutputDto userStatOutputDto = new UserStatOutputDto();
		userStatOutputDto.setId(user.getId());
		userStatOutputDto.setName(user.getName());
		userStatOutputDto.setAge(user.getAge());
		userStatOutputDto.setHeight(user.getHeight());
		userStatOutputDto.setWeight(user.getWeight());
		userStatOutputDto.setGender(user.getGender());
		userStatOutputDto.setStepCounterOutputDtos(stepCounterOutputDtos);
		return userStatOutputDto;
	}
	
	@GetMapping("/month")
	public @ResponseBody UserStatOutputDto getStatMonthByUser(@RequestHeader("token") String token, Integer numberDay){
		
		numberDay = stepCounterService.getDateOfMonth();
		UserOutputDto user = userServiceImpl.getUser(token);
		List<StepCounterOutputDto> stepCounterOutputDtos = stepCounterService.getStatMonthByUser(user.getId(), numberDay);
		
		UserStatOutputDto userStatOutputDto = new UserStatOutputDto();
		userStatOutputDto.setId(user.getId());
		userStatOutputDto.setName(user.getName());
		userStatOutputDto.setAge(user.getAge());
		userStatOutputDto.setHeight(user.getHeight());
		userStatOutputDto.setWeight(user.getWeight());
		userStatOutputDto.setGender(user.getGender());
		userStatOutputDto.setStepCounterOutputDtos(stepCounterOutputDtos);
		return userStatOutputDto;
	}

	@PutMapping("/update")
	public void updateStepCounter(@RequestHeader("token") String token, @RequestBody StepCounterInputDto stepCounterDto) {
		stepCounterDto.setUserId(userServiceImpl.getUser(token).getId());
		// Gọi phương thức của StepCounterService để thực hiện cập nhật
		stepCounterService.updateStepCounter(stepCounterDto);
	}
}
