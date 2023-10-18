package com.example.stepcounter.service.stepcounter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.stepcounter.StepCounterOuputDto;
import com.example.stepcounter.repository.StepCounterRepository;



@Service
public class StepCounterService{
	@Autowired
	private StepCounterRepository stepCounterRepository;
	
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
}
