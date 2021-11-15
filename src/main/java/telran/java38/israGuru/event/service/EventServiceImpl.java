package telran.java38.israGuru.event.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.event.dao.EventPastRepositoty;
import telran.java38.israGuru.event.dao.EventRepositoty;
import telran.java38.israGuru.event.dto.EventDto;
import telran.java38.israGuru.event.dto.EventDtoMini;
import telran.java38.israGuru.event.dto.EventDtoMiniForGuide;
import telran.java38.israGuru.event.dto.NewEventDto;
import telran.java38.israGuru.event.dto.RecordingDto;
import telran.java38.israGuru.event.dto.UpdateEventDto;
import telran.java38.israGuru.event.dto.exceptions.EventNotFoundException;
import telran.java38.israGuru.event.model.Event;
import telran.java38.israGuru.event.model.EventPast;
import telran.java38.israGuru.guide.dao.GuideRepository;
import telran.java38.israGuru.guide.dto.exception.GuideNotFoundException;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepositoty eventRepositoty;
	
	@Autowired
	EventPastRepositoty eventPastRepositoty;
	
	@Autowired
	GuideRepository guideRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public EventDto addNewEvent(String guideId, NewEventDto newEvent) {
		Event event = Event.builder()
				.guideId(guideId)
				.title(newEvent.getTitle())
				.shortDescription(newEvent.getShortDescription())
				.fullDescription(newEvent.getFullDescription())
				.city(newEvent.getCity())
				.date(newEvent.getDate())
				.timeFrom(newEvent.getTimeFrom())
				.timeTo(newEvent.getTimeTo())
				.price(newEvent.getPrice())
				.additionalDescription(newEvent.getAdditionalDescription())
				.images(newEvent.getImages())
				.maximumParticipantsQuantity(newEvent.getMaximumParticipantsQuantity())
				.participantLeft(newEvent.getMaximumParticipantsQuantity())
				.difficultyLevel(newEvent.getDifficultyLevel())
				.language(newEvent.getLanguage())
				.meetingPoint(newEvent.getMeetingPoint())
//				.activityStatus(newEvent.isActivityStatus())
				.build();
		event = eventRepositoty.save(event);
		return modelMapper.map(event, EventDto.class);
	}
	
	@Override
	public EventDto updateEvent(String id, UpdateEventDto updateEvent) {
		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
		if (updateEvent.getTitle() != null) {event.setTitle(updateEvent.getTitle());}
		if (updateEvent.getShortDescription() != null) {event.setShortDescription(updateEvent.getShortDescription());}
		if (updateEvent.getFullDescription() != null) {event.setFullDescription(updateEvent.getFullDescription());}
		if (updateEvent.getCity() != null) {event.setCity(updateEvent.getCity());}
		if (updateEvent.getDate() != null) {
			if (updateEvent.getDate().isBefore(LocalDate.now())) {
				throw new RuntimeException("Event date cannot be in the past");
			}
			event.setDate(updateEvent.getDate());
			}
		if (updateEvent.getTimeFrom() != null) {event.setTimeFrom(updateEvent.getTimeFrom());}
		if (updateEvent.getTimeTo() != null) {event.setTimeTo(updateEvent.getTimeTo());}
		if (updateEvent.getPrice() != null) {event.setPrice(updateEvent.getPrice());}
		if (updateEvent.getAdditionalDescription() != null) {event.setAdditionalDescription(updateEvent.getAdditionalDescription());}
		if (updateEvent.getImages() != null) {event.setImages(updateEvent.getImages());}
		if (updateEvent.getMaximumParticipantsQuantity() != null) {event.setMaximumParticipantsQuantity(updateEvent.getMaximumParticipantsQuantity());}
		if (updateEvent.getParticipantLeft() != null) {event.setParticipantLeft(updateEvent.getParticipantLeft());}
		if (updateEvent.getDifficultyLevel() != null) {event.setDifficultyLevel(updateEvent.getDifficultyLevel());}
		if (updateEvent.getLanguage() != null) {event.setLanguage(updateEvent.getLanguage());}
		if (updateEvent.getMeetingPoint() != null) {event.setMeetingPoint(updateEvent.getMeetingPoint());}
		event = eventRepositoty.save(event);		
		return modelMapper.map(event, EventDto.class);
	}

	@Override
	public boolean activityEvent(String id, String guideId) {
		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
		if (event.getDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Event date cannot be in the past");			
		}
		event.setActivityStatus(true);		
		event = eventRepositoty.save(event);
		return event.isActivityStatus();
	}

//	@Override
//	public boolean disactivityEvent(String id, String guideId) {
//		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
//		event.setActivityStatus(false);
//		event = eventRepositoty.save(event);
//		return !event.isActivityStatus();
//	}

	@Override
	public Iterable<EventDtoMiniForGuide> findAktiveEventsByguideId(String guideId) {
		return eventRepositoty.findByGuideId(guideId)
			.filter(e -> checkDate(e))
			.filter(e -> e.isActivityStatus())
			.map((e) -> modelMapper.map(e, EventDtoMiniForGuide.class))
			.collect(Collectors.toList());
	}

	

	@Override
	public Iterable<EventDtoMiniForGuide> findPastEventsByguideId(String guideId) {
		eventRepositoty.findByGuideId(guideId)
				.filter(e -> checkDate(e));
		return eventPastRepositoty.findByGuideId(guideId)
				.map((e) -> modelMapper.map(e, EventDtoMiniForGuide.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<EventDtoMiniForGuide> findDraftEventsByguideId(String guideId) {
		return eventRepositoty.findByGuideId(guideId)
				.filter(e -> checkDate(e))
				.filter(e -> !e.isActivityStatus())
				.map((e) -> modelMapper.map(e, EventDtoMiniForGuide.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public EventDto removeEventById(String id) {
		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
		eventRepositoty.deleteById(id);
		eventPastRepositoty.save(modelMapper.map(event, EventPast.class));
		return modelMapper.map(event, EventDto.class);
	}
	
	
	
	
//*****************for client*************

	@Override
	public EventDto findEventById(String id) {
		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
		return modelMapper.map(event, EventDto.class);
	}

	
	@Override
	public Iterable<EventDtoMini> findEventsByGuideId(String guideId) {
		if (!guideRepository.existsById(guideId)) {
			throw new GuideNotFoundException(guideId);			
		}
		return eventRepositoty.findByGuideId(guideId)
				.filter(e -> checkDate(e))
				.filter(e -> e.isActivityStatus())				
				.map((e) -> modelMapper.map(e, EventDtoMini.class))
				.collect(Collectors.toList());		
	}

	private boolean checkDate(Event event) {
		if (event.getDate().isBefore(LocalDate.now())) {
			eventRepositoty.deleteById(event.getId());
			eventPastRepositoty.save(modelMapper.map(event, EventPast.class));	
			return false;
		}
		return true;
	}

	@Override
	public Iterable<EventDtoMini> findEventsByCity(String city) {
		return eventRepositoty.findByCity(city)
				.filter(e -> checkDate(e))					
				.filter(e -> e.isActivityStatus())
				.map((e) -> modelMapper.map(e, EventDtoMini.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<EventDtoMini> findEventsByDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		if (localDate.isBefore(LocalDate.now())) {
			throw new RuntimeException("Date " + localDate + " in the past");			
		}
		return eventRepositoty.findByDate(localDate)
				.filter(e -> e.isActivityStatus())
				.map((e) -> modelMapper.map(e, EventDtoMini.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<EventDtoMini> findEventsByLanguage(String language) {
		return eventRepositoty.findByLanguage(language)
				.filter(e -> checkDate(e))
				.filter(e -> e.isActivityStatus())
				.map((e) -> modelMapper.map(e, EventDtoMini.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<EventDtoMini> findEventsByDifficultyLevel(String difficultyLevel) {
		return eventRepositoty.findByDifficultyLevel(difficultyLevel)	
				.filter(e -> checkDate(e))
				.filter(e -> e.isActivityStatus())
				.map((e) -> modelMapper.map(e, EventDtoMini.class))
				.collect(Collectors.toList());
	}

	@Override
	public RecordingDto recordingByEvent(String id, RecordingDto recordingDto) {
		if (recordingDto.getEmail() == null || recordingDto.getName() == null || recordingDto.getPhoneNumber() == null || recordingDto.getNumber() == null) {
			throw new RuntimeException("not all fields are filled");
		}
		Event event = eventRepositoty.findById(id).orElseThrow(()-> new EventNotFoundException(id));
		if (!event.isActivityStatus()) {
			throw new RuntimeException("event not active");
		}
		if (event.getParticipantLeft() < recordingDto.getNumber()) {
			throw new RuntimeException("not enough places");			
		}
		event.getRecordings().add(recordingDto);
		event.setParticipantLeft(event.getParticipantLeft() - recordingDto.getNumber());
		eventRepositoty.save(event);
		return recordingDto;
	}

	

	

}
