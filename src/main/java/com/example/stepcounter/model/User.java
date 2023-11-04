package com.example.stepcounter.model;

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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private int height;
	private int weight;
	private int gender;
	private String token;
	
	@OneToOne(mappedBy = "user")
    private DeviceAccount deviceAccount;
	
	@OneToOne(mappedBy = "user")
    private LinkedAccount linkedAccount;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<StepCounter> listStepCounters;
}
