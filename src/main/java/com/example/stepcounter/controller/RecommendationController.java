package com.example.stepcounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody WeightLoss(@RequestHeader("token") String token){
    	User user = userServiceImpl.getUser(token);
        return re.WeightLossAdvisor(user);
    }
    
    //Giữ dáng
    @GetMapping("/WeightMaintenance")
    public @ResponseBody WeightMaintenance(@RequestHeader("token") String token){
    	User user = userServiceImpl.getUser(token);
        return re.WeightMaintenanceAdvisor(user);
    }
}
