package com.example.stepcounter.dto.user;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEditInputDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private int height;
	private int weight;
	private int gender;
	private String token;
}
