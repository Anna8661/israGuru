package telran.java38.israGuru.guide.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import telran.java38.israGuru.guide.dto.SocialMediaDto;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"guideId"})
@Builder
@Document(collection = "guides")
public class Guide {	
	@Id
	String guideId;
	@Setter
	String email;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	String image;
	@Setter
	List<String> phoneNumbers;
	@Setter
	String license;
	@Setter
	String description;
	@Setter
	String additionalDescription;
	@Setter	
	SocialMediaDto socialMedia;	
	
	public Guide() {
		phoneNumbers = new ArrayList<String>();		
	}
}
