package com.example.stepcounter.service.stepcounter;

import org.mapstruct.Mapper;

import com.example.stepcounter.dto.stepcounter.StepCounterOutDto;
import com.example.stepcounter.model.StepCounter;

@Mapper(componentModel = "spring")
public interface StepCounterMapper {
	StepCounterOutDto getOutputFromEntity(StepCounter stepCounter);
}
