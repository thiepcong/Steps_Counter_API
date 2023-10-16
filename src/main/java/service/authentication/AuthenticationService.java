package service.authentication;

public interface AuthenticationService {
	public String authenticateDevice(String deviceId);
	public String authenticateLinkedAccount(String googleIdToken);
}
