package com.example.stepcounter.dto.recommendation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RecommendationInputDto {
	private int reducedWeight;
	private int durationInDays;
}
