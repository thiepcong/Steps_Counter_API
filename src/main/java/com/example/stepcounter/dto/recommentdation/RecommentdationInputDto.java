package com.example.stepcounter.dto.recommentdation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RecommentdationInputDto {
	private int reducedWeight;
	private int durationInDays;
}
