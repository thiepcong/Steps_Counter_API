package com.example.stepcounter.service.authentication;

import com.example.stepcounter.dto.user.UserInputDto;
import com.example.stepcounter.dto.user.UserLinkedAccountInputDto;

public interface AuthenticationService {
	public String authenticateDevice(String deviceId);
	public String authenticateLinkedAccount(String googleIdToken);
	public String registerDevice(UserInputDto userInputDto);
	public boolean logoutLinkedAccount(String token);
	public String registerLinkedAccount(UserLinkedAccountInputDto userInput);
}
