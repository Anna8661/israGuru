package telran.java38.israGuru.guide.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class GuideConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7371765178413118618L;

	public GuideConflictException(String guideId) {
		super("Guide " + guideId + " is exists");
		
	}
	
	

}
