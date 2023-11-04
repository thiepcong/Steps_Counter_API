package com.example.stepcounter.controller;

import com.example.stepcounter.dto.stepcounter.BagdeOutputDto;
import com.example.stepcounter.service.stepcounter.BadgeService;
import com.example.stepcounter.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;
    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping("")
    public ResponseEntity<BagdeOutputDto> getBadgeOutput(@RequestHeader("token") String token) {
        int userId = userServiceImpl.getUser(token).getId();
        BagdeOutputDto badgeOutput = badgeService.calculateBadgeOutput(userId);
        return new ResponseEntity<>(badgeOutput, HttpStatus.OK);
    }
}