package com.example.stepcounter.dto.authentication;

import lombok.*;

@Data
@AllArgsConstructor
public class OFJDto {
	private String sub;
	private String email;
	private String name;
}
