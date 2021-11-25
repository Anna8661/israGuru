package telran.java38.israGuru.guide.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GuideUpdateDto {
	String firstName;
	String lastName;
	String image;
	List<String> phoneNumbers;
	String license;
	String description;
	String additionalDescription;
	SocialMediaDto socialMedia;	
}
