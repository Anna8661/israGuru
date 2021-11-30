package telran.java38.israGuru.user.dto.get;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class GuideUpdateDto {
	String firstName;
	String lastName;
	String image;
	List<String> phoneNumbers;
	String license;
	String description;
	String additionalDescription;
	Map<String, String> SocialMedia;		
}
