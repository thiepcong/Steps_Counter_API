package com.example.stepcounter.dto.stepcounter;

import java.sql.Date;

public interface StepCounterOuputDto {
	Integer getStepStat();
	Integer getTimeStat();
	Float getDistanceStat();
	Double getCaloStat();
	Date getRealTimeStat();
}
