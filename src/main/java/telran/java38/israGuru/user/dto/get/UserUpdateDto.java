package telran.java38.israGuru.user.dto.get;

import java.util.List;

import lombok.Getter;

@Getter
public class UserUpdateDto {
	String firstName;
	String lastName;
	List<String> phoneNumbers;		
}
