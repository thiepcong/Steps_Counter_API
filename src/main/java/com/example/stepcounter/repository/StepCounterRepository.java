package com.example.stepcounter.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.dto.stepcounter.StepCounterOuputDto;
import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.model.StepCounter;

@Repository
public interface StepCounterRepository extends JpaRepository<StepCounter, Integer>{

//Lấy số ngày trong tháng hiện tại
	@Query(value = "SELECT DAYOFMONTH(LAST_DAY(CURRENT_DATE))", nativeQuery = true)
	Integer getDateOfMonth();
	
//Báo cáo thống kế
	@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime <= CURRENT_DATE ORDER BY sc.realTime DESC LIMIT 7", nativeQuery = true)
	List<StepCounterOuputDto> getStatWeekByUser(@Param("userId") Integer userId);
	
	@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime <= CURRENT_DATE ORDER BY sc.realTime DESC", nativeQuery = true)
    List<StepCounterOuputDto> getStatMonthByUser(@Param("userId") Integer userId);

	@Query(value = "SELECT sc.* FROM stepcounter sc WHERE sc.userid = :userid AND sc.realtime = :now", nativeQuery = true)
	StepCounter getByUserId(@Param("userid") int userid, @Param("now") Date date);
}
