package com.example.stepcounter.dto.recommendation;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RecommendationOutputDto {
	private List<Integer> listCalo;
}
