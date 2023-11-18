package com.example.stepcounter.service.recommendation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.user.UserOutputDto;

@Service
public class RecommendationService {

	public RecommendationService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Integer> WeightLossAdvisor(UserOutputDto user, int reducedWeight, int durationInDays) {
		List<Integer> wl = new ArrayList<>();
        // Tính toán các thông số
        double BMR = calculateBMR(user);
        double totalCaloriesToLose = calculateCaloriesToLoseWeight(reducedWeight, durationInDays, BMR);
        double caloriesPerDay = totalCaloriesToLose / durationInDays;

        // Tạo lộ trình giảm cân theo hàm sigmoid
        for (int day = 1; day <= durationInDays; day++) {
            double caloriesBurned = (sigmoid(day, durationInDays) * caloriesPerDay + caloriesPerDay/2);
            wl.add((int)caloriesBurned);
        }
        return wl;
    }

 // Tính BMR theo công thức Mifflin-St Jeor cho nam hoặc nữ
    public static double calculateBMR(UserOutputDto user) {
        if (user.getGender() == 0) {
            return (88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight()) - (5.677 * user.getAge()));
        } else {
            return (447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight()) - (4.330 * user.getAge()));
        }
    }

    // Hàm tính tổng calo cần để giảm cân
    private static double calculateCaloriesToLoseWeight(int reducedWeight, int durationInDays, double BMR) {
       return (BMR*durationInDays + reducedWeight*7700);
    }

    // Hàm sigmoid để tạo đường cong giảm cân
    private static double sigmoid(int x, int duration) {
        return (1 / (1 + Math.exp(-0.1 * (x - duration / 2))));
    }
    
    //Giữ dáng
    public int WeightMaintenance(UserOutputDto user) {
    	return (int)(calculateBMR(user));
    }
}
