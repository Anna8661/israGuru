package telran.java38.israGuru.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.event.dao.EventRepositoty;
import telran.java38.israGuru.event.model.Event;

@Service("customSecurity")
public class CustomWebSecurityService {
	
	EventRepositoty eventRepositoty;

	@Autowired
	public CustomWebSecurityService(EventRepositoty eventRepositoty) {
		this.eventRepositoty = eventRepositoty;
	}
	
	public boolean checkEventAuthority(String eventId, String emaiGuide) {
		Event event = eventRepositoty.findById(eventId).orElse(null);
		return event == null || emaiGuide.equals(event.getEmailGuide());
		
	}
	
	

}
