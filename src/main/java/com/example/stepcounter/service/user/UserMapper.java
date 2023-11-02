package com.example.stepcounter.service.user;

import org.mapstruct.Mapper;

import com.example.stepcounter.dto.user.UserInputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserOutputDto getOutputFromEntity(User user);
	User getEntityFromInputDto(UserInputDto userInputDto);
}
