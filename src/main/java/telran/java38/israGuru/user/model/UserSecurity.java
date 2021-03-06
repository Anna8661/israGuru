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
@Document(collection = "guidesSecurity")
public class UserSecurity {
	@Id
	@Setter
	String email;
	@Setter
	String password;	

}
