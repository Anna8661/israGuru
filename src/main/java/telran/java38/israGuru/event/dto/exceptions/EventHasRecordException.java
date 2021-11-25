package telran.java38.israGuru.event.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EventHasRecordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3187997754541072301L;

	public EventHasRecordException(String id) {
		super("Event with id = " + id + " already has records");
		}


}
