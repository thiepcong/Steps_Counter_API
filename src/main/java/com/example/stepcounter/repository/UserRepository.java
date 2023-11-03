package com.example.stepcounter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.dto.user.UserStatOutputDto;
import com.example.stepcounter.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User getUserByToken(String token);

	User findById(int id);
	
	@Query(value = "SELECT u.id, u.name, u.age, u.height, u.weight, u.gender,"
			+ "(SELECT SUM(sc.step) FROM stepcounter sc WHERE sc.userId = u.id) as stepChart, "
			+ "(SELECT SUM(sc.time) FROM stepcounter sc WHERE sc.userId = u.id) as timeChart, "
			+ "(SELECT SUM(sc.distance) FROM stepcounter sc WHERE sc.userId = u.id) as distanceChart,"
			+ "(SELECT SUM(sc.calo) FROM stepcounter sc WHERE sc.userId = u.id) as caloChart "
			+ "FROM user u ORDER BY stepChart DESC LIMIT 50", nativeQuery = true)
	List<UserChartOutputDto> getChartAll();
}
