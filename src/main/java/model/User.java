package model;

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
	
	@OneToOne
	@JoinColumn(name = "user_id")
    private DeviceAccount deviceAccount;
	
	@OneToOne
	@JoinColumn(name = "user_id")
    private LinkedAccount linkedAccount;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
//	Collection<>
}
