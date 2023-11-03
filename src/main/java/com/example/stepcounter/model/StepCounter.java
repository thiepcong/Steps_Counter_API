package com.example.stepcounter.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stepcounter")
public class StepCounter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int step;
	private int time;
	private float distance;
	private double calo;
	@Column(name = "realtime")
	private Date realTime;
	
	@JoinColumn(name = "userid")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private User user;
}
