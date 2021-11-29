package telran.java38.israGuru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.java38.israGuru.enumIsraGuru.Role;
import telran.java38.israGuru.user.dao.UserRepository;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.model.User;
import telran.java38.israGuru.user.model.UserSecurity;

@SpringBootApplication
public class IsraGuruServiceApplication implements CommandLineRunner  {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserSecurityRepository userSecurityRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(IsraGuruServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!userSecurityRepository.existsById("admin")) {
			String hashpassword = passwordEncoder.encode("adminIsraGuru");
			UserSecurity adminSecurity= new UserSecurity("admin", hashpassword);
			User admin = new User("admin", "admin", "admin", null);
			admin.addRole(Role.GUIDE);
			admin.addRole(Role.VERIFIEDGUIDE);
			admin.addRole(Role.ADMINISTRATOR);
			userSecurityRepository.save(adminSecurity);	
			userRepository.save(admin);
		}		
	}
}
