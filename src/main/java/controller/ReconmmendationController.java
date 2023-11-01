/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Recommendation;
import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vietn
 */
@RestController
@RequestMapping("/api/recommend")
public class ReconmmendationController {
    private Recommendation re = new Recommendation();
    
    //giảm cân
    @GetMapping("/WeightLoss")
    public int WeightLoss(@RequestBody User user){
        return re.WeightLossAdvisor(user);
    }
    
    //Giữ dáng
    @GetMapping("/WeightMaintenance")
    public int WeightMaintenance(@RequestBody User user){
        return re.WeightMaintenanceAdvisor(user);
    }
}
