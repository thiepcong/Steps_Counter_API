package com.example.stepcounter.service.stepcounter;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.stepcounter.StepCounterOuputDto;
import com.example.stepcounter.dto.stepcounter.StepCounterOutDto;
import com.example.stepcounter.model.StepCounter;
import com.example.stepcounter.model.User;
import com.example.stepcounter.repository.StepCounterRepository;



@Service
public class StepCounterService{
	@Autowired
	private StepCounterRepository stepCounterRepository;
	
	@Autowired
	private StepCounterMapper mapper;
	
	public Integer getDateOfMonth() {
		return stepCounterRepository.getDateOfMonth();
	}

//Báo cáo thống kê
//	public StepCounter getStatDailyByUser(Integer userId) {
//		return stepCounterRepository.getStatDailyByUser(userId);
//	}
	
	public List<StepCounterOuputDto> getStatWeekByUser(Integer userId){
		return stepCounterRepository.getStatWeekByUser(userId);
	}
	
	public List<StepCounterOuputDto> getStatMonthByUser(Integer userId, int number) {
        List<StepCounterOuputDto> allRecords = stepCounterRepository.getStatMonthByUser(userId);

        if (allRecords.size() > number) {
            return allRecords.subList(0, number);
        } else {
            return allRecords;
        }
    }
	public StepCounterOutDto getTodayStepCounter(User user){
		Date currDate = new Date(System.currentTimeMillis());
		StepCounter todayStepCounter =  stepCounterRepository.getByUserId(user.getId(), currDate);
		if(todayStepCounter == null) {
			StepCounter newStepCounter = new StepCounter();
			newStepCounter.setCalo(0);
			newStepCounter.setDistance(0);
			newStepCounter.setRealTime(currDate);
			newStepCounter.setStep(0);
			newStepCounter.setTime(0);
			newStepCounter.setUser(user);
			stepCounterRepository.save(newStepCounter);
			return mapper.getOutputFromEntity(newStepCounter);
		}else {
			return mapper.getOutputFromEntity(todayStepCounter);
		}
	}
}
