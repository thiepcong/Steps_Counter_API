/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.User;

/**
 *
 * @author vietn
 */
public class Recommendation {  

    public Recommendation() {
    }
        
    //Giam can
    public int WeightLossAdvisor(User user) {
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();
        double caloriesPerStep = user.getCalo();

        // Tính lượng calo tiêu thụ cơ bản hàng ngày dựa vào công thức Harris-Benedict
        double bmr = calculateBMR(weight, height, age);

        // Tính số bước đi bộ cần thiết để giảm cân
        int stepsNeeded = calculateStepsNeededWeightLoss(bmr, caloriesPerStep);

        // Đưa ra lời khuyên
//        System.out.println("Để giảm cân, bạn nên đi bộ ít nhất " + stepsNeeded + " bước mỗi ngày.");
        return stepsNeeded;
    }
    
    //Giu dang
    public int WeightMaintenanceAdvisor(User user){
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();
        double caloriesPerStep = user.getCalo();

        // Tính lượng calo tiêu thụ cơ bản hàng ngày dựa vào công thức Harris-Benedict
        double bmr = calculateBMR(weight, height, age);

        // Tính số bước đi bộ cần thiết để giữ dáng
        int stepsNeeded = calculateStepsNeededWeightMaintenance(bmr, caloriesPerStep);

        // Đưa ra lời khuyên
        return stepsNeeded;
    }
    // Hàm tính lượng calo tiêu thụ cơ bản hàng ngày Harris-Benedict
    private static double calculateBMR(double weight, double height, int age) {
        return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
    }
    
    // Hàm tính số bước đi bộ cần thiết để giảm cân
    private static int calculateStepsNeededWeightLoss(double bmr, double caloriesPerStep) {
        
        // Giảm 500 calo/ngày để giảm 0.5 kg mỗi tuần
        double targetCalories = bmr - 500;
        return (int) (targetCalories / caloriesPerStep);
    }
    
    // Hàm tính số bước đi bộ cần thiết để duy trì cân nặng và giữ dáng
    private static int calculateStepsNeededWeightMaintenance(double bmr, double caloriesPerStep) {
        // Số calo cần duy trì dáng vóc hiện tại
        double targetCalories = bmr;

        // Tính số bước cần đi để duy trì cân nặng và giữ dáng
        return (int) (targetCalories / caloriesPerStep);
    }
}
