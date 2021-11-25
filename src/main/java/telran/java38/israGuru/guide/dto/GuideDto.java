package telran.java38.israGuru.guide.dto;
 
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideDto {
	String email;
	String firstName;
	String lastName;
	String image;
	List<String> phoneNumbers;
	String license;
	String description;
	String additionalDescription;
	SocialMediaDto socialMedia;	
	boolean guideVerification;
}
