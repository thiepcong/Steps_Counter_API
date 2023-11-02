package com.example.stepcounter.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
public class UserLinkedAccountInputDto {
	private String idToken;
	private int age;
	private int height;
	private int weight;
	private int gender;
}
