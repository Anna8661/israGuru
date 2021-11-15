package telran.java38.israGuru.event.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class EventDtoMini {
	String id;
	String guideId;
	String title;
	String shortDescription;
	String city;
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate date;
	@JsonFormat(pattern = "HH-mm")
	LocalTime timeFrom;
	@JsonFormat(pattern = "HH-mm")
	LocalTime timeTo;
	PriceDto price;
	List<String> images;
	Integer participantLeft;
	String difficultyLevel;

}
