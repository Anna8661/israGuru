package telran.java38.israGuru.user.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7371765178413118618L;

	public UserConflictException(String userId) {
		super("User " + userId + " is exists");
		
	}
	
	

}
