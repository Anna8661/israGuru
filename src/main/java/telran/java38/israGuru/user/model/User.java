package telran.java38.israGuru.user.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
@Document(collection = "guides")
public class User {	
	@Id
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
	Map<String, String> socialMedia;	
	Set<String> roles;
	
	public User() {
		phoneNumbers = new ArrayList<String>();		
		roles = new HashSet<>();
		roles.add("USER");
		socialMedia = new HashMap<String, String>();
	}

	public User(String email, String firstName, String lastName, List<String> phoneNumbers) {
		this();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumbers = phoneNumbers;
		image = null;
		license = null;
		description = null;
		additionalDescription = null;
	}
	
	public boolean addRole(String role) {
		return roles.add(role);		
	}
	
	public boolean removeRole(String role) {
		return roles.remove(role);
	}
	
	
}
