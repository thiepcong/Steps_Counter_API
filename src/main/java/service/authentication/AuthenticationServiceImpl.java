package service.authentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dto.user.UserOutputDto;
import model.DeviceAccount;
import model.User;
import service.user.UserMapper;
import service.user.UserServiceImpl;
import repository.DeviceAccountRepository;
import repository.LinkedAccountRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private DeviceAccountRepository deviceAccountRepo;
	
	@Autowired
	private LinkedAccountRepository linkedAccountRepo;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String authenticateDevice(String deviceId) {
		DeviceAccount deviceAccount = deviceAccountRepo.getById(deviceId);
		if(deviceAccount != null) {
			return deviceAccount.getUser().getToken();
		}
		return null;
	}

	@Override
	public String authenticateLinkedAccount(String googleIdToken) {
		System.out.println(getUserByGoogleApi(googleIdToken));
		return null;
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
