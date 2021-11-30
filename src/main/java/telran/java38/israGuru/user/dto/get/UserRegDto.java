package telran.java38.israGuru.user.dto.get;

import java.util.List;

import lombok.Getter;

@Getter
public class UserRegDto {
	String email;
	String firstName;
	String lastName;
	List<String> phoneNumbers;
	String password;

}
