package com.example.stepcounter.model;

import java.util.ArrayList;
import java.util.List;

import com.example.stepcounter.dto.user.UserOutputDto;

public class Recommendation {

	public Recommendation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Integer> WeightLossAdvisor(UserOutputDto user, int reducedWeight, int durationInDays) {
		List<Integer> wl = new ArrayList<>();
        // Tính toán các thông số
        int BMR = calculateBMR(user);
        int totalCaloriesToLose = calculateCaloriesToLoseWeight(reducedWeight, durationInDays, BMR);
        int caloriesPerDay = totalCaloriesToLose / durationInDays;

        // Tạo lộ trình giảm cân theo hàm sigmoid
        for (int day = 1; day <= durationInDays; day++) {
            int caloriesBurned = (int) (sigmoid(day, durationInDays) * caloriesPerDay + caloriesPerDay);
            wl.add(caloriesBurned);
        }
        return wl;
    }

 // Tính BMR theo công thức Mifflin-St Jeor cho nam hoặc nữ
    public static int calculateBMR(UserOutputDto user) {
        if (user.getGender() == 0) {
            return (int) (88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight()) - (5.677 * user.getAge()));
        } else {
            return (int) (447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight()) - (4.330 * user.getAge()));
        }
    }

    // Hàm tính tổng calo cần để giảm cân
    private static int calculateCaloriesToLoseWeight(int reducedWeight, int durationInDays, int BMR) {
       return (BMR*durationInDays + reducedWeight*7700);
    }

    // Hàm sigmoid để tạo đường cong giảm cân
    private static int sigmoid(int x, int duration) {
        return (int) (1 / (1 + Math.exp(-0.1 * (x - duration / 2))));
    }
    
    //Giữ dáng
    public int WeightMaintenance(UserOutputDto user) {
    	return calculateBMR(user);
    }
}
