package com.example.stepcounter.dto.stepcounter;

import java.sql.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepCounterOutDto {
	private Integer id;
	private int step;
	private int time;
	private float distance;
	private double calo;
	private Date realTime;
}
