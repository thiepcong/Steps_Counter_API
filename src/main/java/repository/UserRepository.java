package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.dto.user.UserChartOutputDto;

import model.User;

@Repository
public interface UserRepository extends JpaRepository<Integer, User>{

// Lấy user
	User findById(int id);

// Xếp hạng
	// Xếp hạng tổng thể
	@Query(value = "SELECT u.id, u.name, u.age, u.height, u.weight, u.gender,"
			+ "(SELECT SUM(sc.step) FROM stepcounter sc WHERE sc.userId = u.id) as stepStat, "
			+ "(SELECT SUM(sc.time) FROM stepcounter sc WHERE sc.userId = u.id) as timeStat, "
			+ "(SELECT SUM(sc.distance) FROM stepcounter sc WHERE sc.userId = u.id) as distanceStat "
			+ "FROM user u ORDER BY stepStat DESC LIMIT 50", nativeQuery = true)
	List<UserChartOutputDto> getChartAll();
}
