package telran.java38.israGuru.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"email"})
@Builder
@Document(collection = "users")
public class User {	
	@Id
	String email;
	@Setter
	String name;
	@Setter
	String phoneNumber;		
}
