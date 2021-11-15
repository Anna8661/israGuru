package telran.java38.israGuru.guide.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GuideNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542851745035732303L;

	public GuideNotFoundException(String guideId) {
		super("Guide " + guideId + " is not found");
		
	}
	
	

}
