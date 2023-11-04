package com.example.stepcounter.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stepcounter.dto.stepcounter.StepCounterOutputDto;
import com.example.stepcounter.model.StepCounter;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StepCounterRepository extends JpaRepository<StepCounter, Integer>{

//Lấy số ngày trong tháng hiện tại
	@Query(value = "SELECT DAYOFMONTH(LAST_DAY(CURRENT_DATE))", nativeQuery = true)
	Integer getDateOfMonth();
	
//Báo cáo thống kế
	@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime <= CURRENT_DATE ORDER BY sc.realTime DESC LIMIT 7", nativeQuery = true)
	List<StepCounterOutputDto> getStatWeekByUser(@Param("userId") Integer userId);
	
	@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime <= CURRENT_DATE ORDER BY sc.realTime DESC", nativeQuery = true)
    List<StepCounterOutputDto> getStatMonthByUser(@Param("userId") Integer userId);

	@Query(value = "SELECT sc.* FROM stepcounter sc WHERE sc.userid = :userid AND sc.realtime = :now", nativeQuery = true)
	StepCounter getByUserId(@Param("userid") int userid, @Param("now") Date date);


	// Lấy full dữ liệu bảng stepcounter của user
		@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime <= CURRENT_DATE ORDER BY sc.realTime ASC", nativeQuery = true)
	List<StepCounterOutputDto> getStatAllByUser(@Param("userId") Integer userId);

	// Lấy dữ liệu ngày hiện tại của bảng stepcounter của user
		@Query(value = "SELECT sc.step as stepStat, sc.time as timeStat, sc.distance as distanceStat, sc.calo as caloStat, sc.realTime as realTimeStat "
			+ "FROM stepcounter sc WHERE sc.userId = :userId AND sc.realTime = :currentDate", nativeQuery = true)
		StepCounterOutputDto getStatCurrentDateByUser(@Param("userId") Integer userId, @Param("currentDate") Date currentDate);

	// Update data stepcounter trong ngày
	@Transactional
	@Modifying
	@Query("UPDATE StepCounter sc SET sc.step = :step, sc.time = :time, sc.distance = :distance, sc.calo = :calo WHERE sc.user.id = :userId AND sc.realTime = :currentDate")
	void updateStepCounter(@Param("userId") Integer userId, @Param("currentDate") Date currentDate,
						   @Param("step") int step, @Param("time") int time, @Param("distance") float distance,
						   @Param("calo") double calo);
}
