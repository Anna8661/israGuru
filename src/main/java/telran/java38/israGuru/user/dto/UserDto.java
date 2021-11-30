package telran.java38.israGuru.user.dto;
 
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	String email;
	String name;
	List<String> phoneNumbers;
}
