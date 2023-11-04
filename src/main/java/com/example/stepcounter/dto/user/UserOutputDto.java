package com.example.stepcounter.dto.user;

import java.util.List;

import com.example.stepcounter.dto.stepcounter.StepCounterOutDto;
import com.example.stepcounter.model.StepCounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
	private int id;
	private String name;
	private int age;
	private int height;
	private int weight;
	private int gender;
	StepCounterOutDto todayStepCounter;
}
