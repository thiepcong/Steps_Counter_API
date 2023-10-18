package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Web.service.user.UserServiceImpl;
import com.example.stepcounter.dto.user.UserChartOutputDto;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
//Xếp hạng theo tháng
	@GetMapping("/chart")
	public @ResponseBody List<UserChartOutputDto> getChartAll(){
		return userServiceImpl.getChartAll();
	}	
}
