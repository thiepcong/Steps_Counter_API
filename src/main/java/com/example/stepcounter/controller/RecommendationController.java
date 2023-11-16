package com.example.stepcounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.model.Recommendation;
import com.example.stepcounter.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/recommend")
public class ReconmmendationController {
	@Autowired
    private Recommendation re;
    
    @Autowired
	private UserServiceImpl userServiceImpl;
    
    //giảm cân
    @GetMapping("/WeightLoss")
    public @ResponseBody List<Integer> WeightLoss(@RequestHeader("token") String token, @RequestParam("reducedWeight") int reducedWeight, @RequestParam("durationInDays") int durationInDays){
    	UserOutputDto user = UserServiceImpl.getUser(token);
        return re.WeightLossAdvisor(user, reducedWeight, durationInDays);
    }
    
    //Giữ dáng
    @GetMapping("/WeightMaintenance")
    public @ResponseBody int WeightMaintenance(@RequestHeader("token") String token){
    	UserOutputDto user = userServiceImpl.getUser(token);
        return re.WeightMaintenanceAdvisor(user);
    }
}
