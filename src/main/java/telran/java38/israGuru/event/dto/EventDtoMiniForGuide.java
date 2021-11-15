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
public class EventDtoMiniForGuide {
	String id;
	String title;
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate date;
	PriceDto price;
	Integer maximumParticipantsQuantity;
	Integer participantLeft;
}
