package com.example.stepcounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.common.AuthenticationResponseApi;
import com.example.stepcounter.common.ResponseApi;
import com.example.stepcounter.dto.deviceaccount.DeviceIDInputDto;
import com.example.stepcounter.dto.linkedaccount.GoogleTokenInputDto;
import com.example.stepcounter.dto.user.UserInputDto;
import com.example.stepcounter.dto.user.UserLinkedAccountInputDto;
import com.example.stepcounter.service.authentication.AuthenticationServiceImpl;
import com.example.stepcounter.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired 
	private AuthenticationServiceImpl authService;
	
	@Autowired 
	private UserServiceImpl userService;
	
	@GetMapping("/device")
	public ResponseEntity<AuthenticationResponseApi> authenticateByDeviceId(@RequestBody DeviceIDInputDto deviceIdInput){
		String token = authService.authenticateDevice(deviceIdInput.getDeviceId());
		if(token != null) {
			return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(true, token, false), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(false, "", true), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/register/device")
	public ResponseEntity<AuthenticationResponseApi> registerDevice(@RequestBody UserInputDto userInputDto){
		String newToken = authService.registerDevice(userInputDto);
		if(newToken != null) {
			return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(true, newToken, false), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(false, "", true), HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/googleaccount/logout")
	public ResponseEntity<ResponseApi> logoutLinkedAccount(@RequestHeader("idtoken") String googleIdToken) {
		if(userService.refreshToken(googleIdToken)) {
			return new ResponseEntity<ResponseApi>(new ResponseApi(true, "Đăng xuất thành công!"), HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<ResponseApi>(new ResponseApi(false, "Đăng xuất không thành công!"), HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/googleaccount")
	public ResponseEntity<AuthenticationResponseApi> authenticateByGoogleToken(@RequestBody GoogleTokenInputDto googleIdToken){
		String token = authService.authenticateLinkedAccount(googleIdToken.getIdToken());
		if(token == null) {
			return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(false, "", true), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(true, token, false), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register/google/account")
	public ResponseEntity<AuthenticationResponseApi> registerLinkedAccount(@RequestBody UserLinkedAccountInputDto userInputDto){
		String newToken = authService.registerLinkedAccount(userInputDto);
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(true,newToken, false), HttpStatus.ACCEPTED);
	}
}
