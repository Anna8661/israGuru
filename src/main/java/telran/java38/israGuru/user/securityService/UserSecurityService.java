package telran.java38.israGuru.user.securityService;

import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.guide.dto.exception.ForbiddenException;
import telran.java38.israGuru.guide.dto.exception.GuideAuthException;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.dto.exception.UserAuthException;
import telran.java38.israGuru.user.model.UserSecurity;

@Service
public class UserSecurityService {
	@Autowired
	UserSecurityRepository userSecurityRepository;
	
	public void updateUserValidate(String userEmail, String token) throws UserAuthException {
		UserCredential credential = decodeToken(token);
		UserSecurity userSecurity = userSecurityRepository.findById(credential.getIdString()).orElseThrow(() -> new UserAuthException());
		if (!BCrypt.checkpw(credential.getPassword(), userSecurity.getPassword())) {
			throw new UserAuthException();			
		}
		if (!userSecurity.getEmail().equals(userEmail)) {
			throw new ForbiddenException();				
		}
		
	}
	
	private UserCredential decodeToken (String auth) throws UserAuthException {
		try {
			int index = auth.indexOf(" ");
			auth = auth.substring(index + 1);
			byte[] byteDecode = Base64.getDecoder().decode(auth);
			String token = new String(byteDecode);
			String [] credentials = token.split(":");
			return new UserCredential (credentials[0], credentials[1]);
		} catch (Exception e) {
			throw new UserAuthException();
		}

}


}
