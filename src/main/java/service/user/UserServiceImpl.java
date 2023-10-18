package service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepcounter.dto.user.UserChartOutputDto;
import com.example.stepcounter.modal.User;
import com.example.stepcounter.repository.UserRepository;

@Service

public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	//Lấy user
	public User getUserById(int id) {
		return userRepository.findById(id);
	}
	
	// Xếp hạng
	public List<UserChartOutputDto> getChartAll() {
		return userRepository.getChartAll();
	}
}
