package com.example.stepcounter.service.authentication;

import java.util.Arrays;
import java.util.UUID;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType; 
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.stepcounter.dto.authentication.OFJDto;
import com.example.stepcounter.dto.user.UserInputDto;
import com.example.stepcounter.dto.user.UserLinkedAccountInputDto;
import com.example.stepcounter.model.DeviceAccount;
import com.example.stepcounter.model.LinkedAccount;
import com.example.stepcounter.model.User;
import com.example.stepcounter.service.user.UserMapper;
import com.example.stepcounter.service.user.UserServiceImpl;
import com.example.stepcounter.repository.DeviceAccountRepository;
import com.example.stepcounter.repository.LinkedAccountRepository;
import com.example.stepcounter.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private DeviceAccountRepository deviceAccountRepo;
	
	@Autowired
	private LinkedAccountRepository linkedAccountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public String authenticateDevice(String deviceId) {
		DeviceAccount deviceAccount = deviceAccountRepo.getById(deviceId);
		if(deviceAccount != null) {
			return deviceAccount.getUser().getToken();
		}
		return null;
	}
	
	@Override
	public String registerDevice(UserInputDto userInputDto) {
		User user = mapper.getEntityFromInputDto(userInputDto);
		user.setToken(UUID.randomUUID().toString());
		userRepo.save(user);
		DeviceAccount newDeviceAccount = new DeviceAccount();
		newDeviceAccount.setId(userInputDto.getDeviceId());
		newDeviceAccount.setUser(user);
		deviceAccountRepo.save(newDeviceAccount);
		return user.getToken();
	}
	@Override
	public String authenticateLinkedAccount(String googleIdToken) {
		String info = getUserByGoogleApi(googleIdToken);
		Gson gson = new Gson(); 
	    OFJDto oFJDto = gson.fromJson(info, OFJDto.class);
	    LinkedAccount linkedAccount = linkedAccountRepo.getById(oFJDto.getSub());
	    if(linkedAccount != null){
	    	return linkedAccount.getUser().getToken();
	    }
	    return null;
	}
	
	@Override
	public String registerLinkedAccount(UserLinkedAccountInputDto userInput) {
		String info = getUserByGoogleApi(userInput.getIdToken());
		Gson gson = new Gson(); 
	    OFJDto oFJDto = gson.fromJson(info, OFJDto.class);

    	User user = new User();
    	user.setName(oFJDto.getName());
    	user.setToken(UUID.randomUUID().toString());
    	user.setAge(userInput.getAge());
    	user.setHeight(userInput.getHeight());
    	user.setWeight(userInput.getWeight());
    	user.setGender(userInput.getGender());
    	User newUser = userRepo.save(user);
    	
    	LinkedAccount newLinkedAccount = new LinkedAccount();
    	newLinkedAccount.setId(oFJDto.getSub());
    	newLinkedAccount.setUser(newUser);  
    	linkedAccountRepo.save(newLinkedAccount);	 
    	return newLinkedAccount.getUser().getToken();
	}
	@Override
	public boolean logoutLinkedAccount(String token) {
		return userService.refreshToken(token);
	}
	
	public void refreshToken() {
		
	}
	public String getUserByGoogleApi(String googleIdToken) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	    return restTemplate.exchange(
	    		"https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleIdToken, 
	    			HttpMethod.GET, entity, String.class).getBody();
	}
	
}
