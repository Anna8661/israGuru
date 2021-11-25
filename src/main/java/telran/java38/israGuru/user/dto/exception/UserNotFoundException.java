package telran.java38.israGuru.user.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3058435727853015397L;

	public UserNotFoundException(String userId) {
		super("User " + userId + " is not found");
		
	}
	
	

}
