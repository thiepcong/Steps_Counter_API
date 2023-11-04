package com.example.stepcounter.service.stepcounter;

public class CalorieCalculator {
    public static double calculateCalories(int weight, int height, int age, int gender, int timeInMinute, double distanceInMeter) {
        double meterPerSecond = distanceInMeter/(timeInMinute*60);

        double heightInMeter = (double) height/100;
        double rawCalories = (0.035*weight+(meterPerSecond*2)/heightInMeter)*0.029*weight*timeInMinute;
        double ageFactor = age>=16&&age<=30?1:0.9;
        double genderFactor = gender==1?1.1:0.9;
        double calories = rawCalories*ageFactor*genderFactor;
        return calories;
    }
}
