package com.example.stepcounter.dto.stepcounter;

import java.sql.Date;

public interface StepCounterOutputDto {
	Integer getStepStat();
	Integer getTimeStat();
	Float getDistanceStat();
	Double getCaloStat();
	Date getRealTimeStat();
}
