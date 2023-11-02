package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.AuthenticationResponseApi;
import dto.user.UserOutputDto;
import service.authentication.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired 
	private AuthenticationServiceImpl authService;
	
	@GetMapping("/device")
	public ResponseEntity<AuthenticationResponseApi> authenticateByDeviceId(@RequestHeader("device_id") String deviceId){
		String token = authService.authenticateDevice(deviceId);
		if(token != null) {
			return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(true, token), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(false, ""), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/googleaccount")
	public ResponseEntity<AuthenticationResponseApi> authenticateByGoogleToken(@RequestHeader("idtoken") String googleIdToken){
		String token = authService.authenticateLinkedAccount(googleIdToken);
		System.out.println(googleIdToken);
		return new ResponseEntity<AuthenticationResponseApi>(new AuthenticationResponseApi(false, ""), HttpStatus.NOT_FOUND);
	}
}
