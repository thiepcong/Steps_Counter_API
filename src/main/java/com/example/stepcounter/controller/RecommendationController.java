package com.example.stepcounter.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.stepcounter.dto.recommentdation.RecommendationInputDto;
import com.example.stepcounter.dto.recommentdation.RecommendationOutputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.service.recommentdation.RecommendationService;
import com.example.stepcounter.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {
	@Autowired
    private RecommendationService re;
    
    @Autowired
	private UserServiceImpl userServiceImpl;
    
    //giảm cân
    @GetMapping("/WeightLoss")
    public @ResponseBody RecommendationOutputDto WeightLoss(@RequestHeader("token") String token, @RequestBody RecommendationInputDto rcInputDto){
    	UserOutputDto user = userServiceImpl.getUser(token);
    	
        List<Integer> res = re.WeightLossAdvisor(user, rcInputDto.getReducedWeight(), rcInputDto.getDurationInDays());
        RecommendationOutputDto respon = new RecommendationOutputDto();
        respon.setListCalo(res);
        return respon;
    }
    
    //Giữ dáng
    @GetMapping("/WeightMaintenance")
    public @ResponseBody RecommendationOutputDto WeightMaintenance(@RequestHeader("token") String token){
    	UserOutputDto user = userServiceImpl.getUser(token);
        int x = re.WeightMaintenance(user);
        List<Integer> res = new ArrayList<>();
        res.add(x);
        RecommendationOutputDto respon = new RecommendationOutputDto();
        respon.setListCalo(res);
        return respon;
    }
}
