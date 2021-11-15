package telran.java38.israGuru.guide.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class NewGuideDto {
	String guideId;
	String email;
	String firstName;
	String lastName;
	String image;
	List<String> phoneNumbers;
	String license;
	String description;
	String additionalDescription;
	SocialMediaDto socialMedia;	

}
