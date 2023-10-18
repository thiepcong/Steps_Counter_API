package com.example.stepcounter.modal;

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
	
	@Column(name = "realtime")
	private Date realTime;
	
	@JoinColumn(name = "userid")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private User user;
}
