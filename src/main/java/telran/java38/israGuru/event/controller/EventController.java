package telran.java38.israGuru.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.israGuru.event.dto.EventDto;
import telran.java38.israGuru.event.dto.EventDtoMini;
import telran.java38.israGuru.event.dto.EventDtoMiniForGuide;
import telran.java38.israGuru.event.dto.NewEventDto;
import telran.java38.israGuru.event.dto.RecordingDto;
import telran.java38.israGuru.event.dto.UpdateEventDto;
import telran.java38.israGuru.event.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventService;
	
	@PostMapping("/{guideId}")
	public EventDto addNewEvent (@PathVariable String guideId, @RequestBody NewEventDto newEvent) {
		return eventService.addNewEvent(guideId, newEvent);
	}
	
	@GetMapping("/{id}")
	public EventDto getEventById(@PathVariable String id) {
		return eventService.findEventById(id);
	}
	
	@PutMapping("/{id}")
	public EventDto updateEvent(@PathVariable String id, @RequestBody UpdateEventDto updateEvent) {
		return eventService.updateEvent(id, updateEvent);
	}
	
	@PutMapping("/{id}/activity/{guideId}")
	public boolean activityEvent(@PathVariable String id, @PathVariable String guideId) {
		return eventService.activityEvent(id, guideId);
	}
	
//	@PutMapping("/{id}/disActivity/{guideId}")
//	public boolean disactivityEvent(@PathVariable String id, @PathVariable String guideId) {
//		return eventService.disactivityEvent(id, guideId);
//	}
	
	@GetMapping("/guide/{guideId}")
	public Iterable<EventDtoMini> getEventsByGuideId(@PathVariable String emaleGuide) {
		return eventService.findEventsByEmailGuide(emaleGuide);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<EventDtoMini> getEventsByCity(@PathVariable String city) {
		return eventService.findEventsByCity(city);
	}
	
	@GetMapping("/date/{date}")
	public Iterable<EventDtoMini> getEventsByDate(@PathVariable String date) {
		return eventService.findEventsByDate(date);
	}

	@GetMapping("/language/{language}")
	public Iterable<EventDtoMini> getEventsByLanguage(@PathVariable String language) {
		return eventService.findEventsByLanguage(language);
	}
	
	@GetMapping("/level/{level}")
	public Iterable<EventDtoMini> getEventsByDifficultyLevel(@PathVariable String level) {
		return eventService.findEventsByDifficultyLevel(level);
	}
	
	@GetMapping("/aktive/{guideId}")
	public Iterable<EventDtoMiniForGuide> getActiveEventsByEmaleGuide(@PathVariable String emaleGuide) {
		return eventService.findAktiveEventsByEmailGuide(emaleGuide);
	}
	
	@GetMapping("/past/{guideId}")
	public Iterable<EventDtoMiniForGuide> getPastEventsByGuideId(@PathVariable String emaleGuide) {
		return eventService.findPastEventsByEmailGuide(emaleGuide);
	}
	
	@GetMapping("/draft/{guideId}")
	public Iterable<EventDtoMiniForGuide> getDraftEventsByEmaleGuide(@PathVariable String emaleGuide) {
		return eventService.findDraftEventsByEmailGuide(emaleGuide);
	}
	
	@DeleteMapping("/{id}")
	public EventDto removeEventById(@PathVariable String id) {
		return eventService.removeEventById(id);
	}
	
	@PutMapping("/recording/{eventId}")
	public RecordingDto recordingByEvent(@PathVariable String eventId, @RequestBody RecordingDto recordingDto) {
		return eventService.recordingByEvent(eventId, recordingDto);
	}


	

}
