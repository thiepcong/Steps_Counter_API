package com.example.stepcounter.dto.deviceaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.stepcounter.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAccountInputDto {
	private String id;
	private User user;
}
