package com.example.stepcounter.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponseApi {
	private boolean success;
	private String token;
	private boolean firstLogin;
}
