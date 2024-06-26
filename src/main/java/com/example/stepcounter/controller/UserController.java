package com.example.stepcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.model.User;
import com.example.stepcounter.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestHeader("token") String token, @RequestBody User updatedUser) {
		try {
			UserOutputDto user = userServiceImpl.getUser(token);
			User result = userServiceImpl.updateUser(token, user, updatedUser);
			System.out.println(result);
			return ResponseEntity.ok().body("{\"status\": \"success\", \"message\": \"Update successful!\"}");
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{\"status\": \"failure\", \"message\": \""+ e.getMessage()+"\"}");
		}
    }
	
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
