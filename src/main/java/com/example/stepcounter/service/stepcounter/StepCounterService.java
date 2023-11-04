package com.example.stepcounter.service.stepcounter;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

import com.example.stepcounter.dto.stepcounter.StepCounterInputDto;
import com.example.stepcounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.stepcounter.StepCounterOutputDto;
import com.example.stepcounter.dto.stepcounter.StepCounterOutDto;
import com.example.stepcounter.model.StepCounter;
import com.example.stepcounter.model.User;
import com.example.stepcounter.repository.StepCounterRepository;

import static com.example.stepcounter.service.stepcounter.CalorieCalculator.calculateCalories;


@Service
public class StepCounterService{
	@Autowired
	private StepCounterRepository stepCounterRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StepCounterMapper mapper;
	
	public Integer getDateOfMonth() {
		return stepCounterRepository.getDateOfMonth();
	}

//Báo cáo thống kê
//	public StepCounter getStatDailyByUser(Integer userId) {
//		return stepCounterRepository.getStatDailyByUser(userId);
//	}
	
	public List<StepCounterOutputDto> getStatWeekByUser(Integer userId){
		return stepCounterRepository.getStatWeekByUser(userId);
	}
	
	public List<StepCounterOutputDto> getStatMonthByUser(Integer userId, int number) {
        List<StepCounterOutputDto> allRecords = stepCounterRepository.getStatMonthByUser(userId);

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


	@Async
	public void updateStepCounter(StepCounterInputDto stepCounterInputDto) {
		// Lấy thông tin người dùng từ cơ sở dữ liệu dựa trên userId
		User user = userRepository.findById(stepCounterInputDto.getUserId());

		if (user == null) {
			// Xử lý logic khi không tìm thấy người dùng
			return;
		}

		// Tính toán giá trị calo dựa trên step, time và distance
		double calo = calculateCalories(user.getWeight(), user.getHeight(), user.getAge(), user.getGender(),
				stepCounterInputDto.getTime(), stepCounterInputDto.getDistance());

		long millis=System.currentTimeMillis();
		Date currentDate = new Date(millis);
		stepCounterInputDto.setRealTime(currentDate);
		StepCounterOutputDto stepCounterOutputDto =
				stepCounterRepository.getStatCurrentDateByUser(stepCounterInputDto.getUserId(), currentDate);

		if(stepCounterOutputDto != null){
			stepCounterInputDto.setStep(stepCounterInputDto.getStep() + stepCounterOutputDto.getStepStat());
			stepCounterInputDto.setTime(stepCounterInputDto.getTime() + stepCounterOutputDto.getTimeStat());
			stepCounterInputDto.setDistance(stepCounterInputDto.getDistance() + stepCounterOutputDto.getDistanceStat());
			calo += stepCounterOutputDto.getCaloStat();
		}

		DecimalFormat df = new DecimalFormat("#,###.#");
		df.format(calo);
		// Tạo một đối tượng StepCounter từ StepCounterInputDto
		StepCounter stepCounter = new StepCounter();
		stepCounter.setStep(stepCounterInputDto.getStep());
		stepCounter.setTime(stepCounterInputDto.getTime());
		stepCounter.setDistance(stepCounterInputDto.getDistance());
		stepCounter.setCalo(calo);
		stepCounter.setRealTime(stepCounterInputDto.getRealTime());
		stepCounter.setUser(user);

		// Lưu thông tin bước chân vào cơ sở dữ liệu
		if(stepCounterOutputDto != null){
			stepCounterRepository.updateStepCounter(stepCounter.getUser().getId(), stepCounter.getRealTime(), stepCounter.getStep(),
					stepCounter.getTime(), stepCounter.getDistance(), calo);
		}else{
			stepCounterRepository.save(stepCounter);
		}
	}
}
