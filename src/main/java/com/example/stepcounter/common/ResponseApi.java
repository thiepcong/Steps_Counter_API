package com.example.stepcounter.common;

import lombok.*;

@Data
@AllArgsConstructor
public class ResponseApi {
	private boolean success;
	private String message;
}
