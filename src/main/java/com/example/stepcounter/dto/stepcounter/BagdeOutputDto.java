package com.example.stepcounter.dto.stepcounter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class BagdeOutputDto {
    private int totalSteps;
    private int maxStepsInDay;
    private double totalDistance;
    private int maxConsecutiveWalkingDays;
    private int level;
}
