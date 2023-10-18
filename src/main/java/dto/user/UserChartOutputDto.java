package com.example.stepcounter.dto.user;

import java.sql.Date;

import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface UserChartOutputDto{
	Integer getId();
	String getName();
	Integer getAge();
	Integer getHeight();
	Integer getWeight();
	Integer getGender();
	Integer getStepStat();
	Integer getTimeStat();
	Float getDistanceStat();
	Date getRealTimeStat();
}
