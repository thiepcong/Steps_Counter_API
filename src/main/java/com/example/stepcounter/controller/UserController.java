package com.example.stepcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.enums.ErrorCode;
import com.example.stepcounter.exceptions.CommandException;
import com.example.stepcounter.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping
	public UserOutputDto getUser(@RequestHeader("token") String token) {
		UserOutputDto userOutput = userServiceImpl.getUser(token);
		return userOutput;
	}
	
	@GetMapping("/chart")
	public @ResponseBody List<UserChartOutputDto> getChartAll(){
		return userServiceImpl.getChartAll();
	}
}
