package telran.java38.israGuru.event.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import telran.java38.israGuru.event.dto.MeetingPointDto;
import telran.java38.israGuru.event.dto.PriceDto;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
@AllArgsConstructor
@Builder
@Document(collection = "eventsPast")
public class EventPast {
	@Id
	String id;
	String emailGuide;
	@Setter
	String title;
	@Setter
	String shortDescription;
	@Setter
	String fullDescription;
	@Setter
	String city;
	@Setter
	LocalDate date;
	@Setter
	LocalTime timeFrom;
	@Setter
	LocalTime timeTo;
	@Setter
	PriceDto price;
	@Setter
	String additionalDescription;
	@Setter
	List<String> images;
	@Setter
	Integer maximumParticipantsQuantity;
	@Setter
	Integer participantLeft;
	@Setter
	String difficultyLevel;
	@Setter
	String language;
	@Setter
	MeetingPointDto meetingPoint;
	@Setter
	String activityStatus;
	
	

	public EventPast() {
		images = new ArrayList<String>();
		activityStatus = "REMOVED";		
	}
	
	
	
	
	
}





