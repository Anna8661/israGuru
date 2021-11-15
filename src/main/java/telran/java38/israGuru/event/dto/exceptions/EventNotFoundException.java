package telran.java38.israGuru.event.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3187997754541072301L;

	public EventNotFoundException(String id) {
		super("Event with id = " + id + " not found");
		}


}
