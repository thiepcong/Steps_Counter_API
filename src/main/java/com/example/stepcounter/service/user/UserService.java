package com.example.stepcounter.service.user;

import com.example.stepcounter.dto.user.UserOutputDto;

public interface UserService {
	public UserOutputDto getUser(String token);
}
