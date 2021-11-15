package telran.java38.israGuru.event.dao;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.event.model.Event;
import telran.java38.israGuru.event.model.EventPast;

public interface EventPastRepositoty extends MongoRepository<EventPast, String>{
	
	Stream <Event> findByGuideId(String guideId);
//	Stream<Event> findByCity(String city);
//	Stream<Event> findByDate(LocalDate date);
//	Stream<Event> findByLanguage(String language);
//	Stream<Event> findByDifficultyLevel(String difficultyLevel);


}
