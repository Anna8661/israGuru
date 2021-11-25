package telran.java38.israGuru.event.service;

import telran.java38.israGuru.event.dto.EventDto;
import telran.java38.israGuru.event.dto.EventDtoMini;
import telran.java38.israGuru.event.dto.EventDtoMiniForGuide;
import telran.java38.israGuru.event.dto.NewEventDto;
import telran.java38.israGuru.event.dto.RecordingDto;
import telran.java38.israGuru.event.dto.UpdateEventDto;

public interface EventService {
	EventDto addNewEvent(String id, NewEventDto newEvent);

	EventDto findEventById(String id);

	EventDto updateEvent(String id, UpdateEventDto updateEvent);

	boolean activityEvent(String id, String guideId);

	boolean disactivityEvent(String id, String guideId);

	Iterable<EventDtoMini> findEventsByEmailGuide(String guideId);

	Iterable<EventDtoMini> findEventsByCity(String city);

	Iterable<EventDtoMini> findEventsByDate(String date);

	Iterable<EventDtoMini> findEventsByLanguage(String language);

	Iterable<EventDtoMini> findEventsByDifficultyLevel(String difficultyLevel);

	Iterable<EventDtoMiniForGuide> findAktiveEventsByEmailGuide(String emailGuide);

	Iterable<EventDtoMiniForGuide> findPastEventsByEmailGuide(String emailGuide);

	Iterable<EventDtoMiniForGuide> findDraftEventsByEmailGuide(String emailGuide);

	EventDto removeEventById(String id);

	RecordingDto recordingByEvent(String eventId, RecordingDto recordingDto);

}
