package telran.java38.israGuru.event.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.event.model.Event;

public interface EventRepositoty extends MongoRepository<Event, String>{
	
	Stream <Event> findByGuideId(String guideId);
	Stream<Event> findByCity(String city);
	Stream<Event> findByDate(LocalDate date);
	Stream<Event> findByLanguage(String language);
	Stream<Event> findByDifficultyLevel(String difficultyLevel);


}
