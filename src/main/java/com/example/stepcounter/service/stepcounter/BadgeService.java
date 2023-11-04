package com.example.stepcounter.service.stepcounter;

import com.example.stepcounter.dto.stepcounter.BagdeOutputDto;
import com.example.stepcounter.dto.stepcounter.StepCounterOutputDto;
import com.example.stepcounter.repository.StepCounterRepository;
import com.example.stepcounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class BadgeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StepCounterRepository stepCounterRepository;

    public BagdeOutputDto calculateBadgeOutput(Integer userId) {
        List<StepCounterOutputDto> stepCounters = stepCounterRepository.getStatAllByUser(userId);

        int totalDailySteps = calculateTotalSteps(stepCounters);
        int maxConsecutiveDays = calculateMaxConsecutiveWalkingDays(stepCounters);
        int maxStepsInDay = calculateMaxStepsInDay(stepCounters);
        double totalWalkingDistance = calculateTotalWalkingDistance(stepCounters);

        BagdeOutputDto badgeOutput = new BagdeOutputDto();
        badgeOutput.setTotalSteps(totalDailySteps);
        badgeOutput.setMaxStepsInDay(maxStepsInDay);
        badgeOutput.setTotalDistance(totalWalkingDistance);
        badgeOutput.setMaxConsecutiveWalkingDays(maxConsecutiveDays);
        badgeOutput.setLevel(totalDailySteps/10000);
        return badgeOutput;
    }
    public int calculateTotalSteps(List<StepCounterOutputDto> stepCounters) {
        int totalSteps = 0;
        for (StepCounterOutputDto stepCounter : stepCounters) {
            totalSteps += stepCounter.getStepStat();
        }
        return totalSteps;
    }

    public int calculateMaxConsecutiveWalkingDays(List<StepCounterOutputDto> stepCounters) {
        int maxConsecutiveDays = 0;
        int currentConsecutiveDays = 0;
        for (int i = 0; i < stepCounters.size() - 1; i++) {
            LocalDate currentDate = stepCounters.get(i).getRealTimeStat().toLocalDate();
            LocalDate nextDate = stepCounters.get(i + 1).getRealTimeStat().toLocalDate();

            if (currentDate.plusDays(1).isEqual(nextDate)) {
                currentConsecutiveDays++;
            } else {
                currentConsecutiveDays = 0;
            }

            if (currentConsecutiveDays > maxConsecutiveDays) {
                maxConsecutiveDays = currentConsecutiveDays;
            }
        }
        return maxConsecutiveDays+1;
    }

    public int calculateMaxStepsInDay(List<StepCounterOutputDto> stepCounters) {
        int maxStepsInDay = 0;
        for (StepCounterOutputDto stepCounter : stepCounters) {
            if (stepCounter.getStepStat() > maxStepsInDay) {
                maxStepsInDay = stepCounter.getStepStat();
            }
        }
        return maxStepsInDay;
    }

    public double calculateTotalWalkingDistance(List<StepCounterOutputDto> stepCounters) {
        double totalDistance = 0;
        for (StepCounterOutputDto stepCounter : stepCounters) {
            totalDistance += stepCounter.getDistanceStat();
        }
        DecimalFormat df = new DecimalFormat("#,###.#");
        df.format(totalDistance);
        return totalDistance;
    }
}