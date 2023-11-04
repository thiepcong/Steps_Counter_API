package com.example.stepcounter.dto.stepcounter;

import lombok.Data;

import java.sql.Date;

@Data
public class StepCounterInputDto {
    private Integer id;
    private int step;
    private int time;
    private float distance;
    private Date realTime;
    private int userId;
}