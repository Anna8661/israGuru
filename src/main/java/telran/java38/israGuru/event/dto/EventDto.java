package telran.java38.israGuru.event.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
	String id;
	String emailGuide;
	String title;
	String shortDescription;
	String fullDescription;
	String city;
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate date;
	@JsonFormat(pattern = "HH-mm")
	LocalTime timeFrom;
	@JsonFormat(pattern = "HH-mm")
	LocalTime timeTo;
	PriceDto price;
	String additionalDescription;
	List<String> images;
	Integer maximumParticipantsQuantity;
	Integer participantLeft;
	String difficultyLevel;
	String language;
	MeetingPointDto meetingPoint;
	String activityStatus;


}
