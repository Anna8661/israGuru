package telran.java38.israGuru.user.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserAuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9197221398595170019L;

	
}
