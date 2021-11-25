package telran.java38.israGuru.guide.securityService;

import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.guide.dao.GuideSecurityRepository;
import telran.java38.israGuru.guide.dto.exception.ForbiddenException;
import telran.java38.israGuru.guide.dto.exception.GuideAuthException;
import telran.java38.israGuru.guide.model.GuideSecurity;

@Service
public class GuideSecurityService {
	@Autowired
	GuideSecurityRepository guideSecurityRepository;
	
	public void updateGuideValidate(String guideEmail, String token) throws GuideAuthException {
		GuideCredential credential = decodeToken(token);
		GuideSecurity guideSecurity = guideSecurityRepository.findById(credential.getIdString()).orElseThrow(() -> new GuideAuthException());
		if (!BCrypt.checkpw(credential.getPassword(), guideSecurity.getPassword())) {
			throw new GuideAuthException();			
		}
		if (!guideSecurity.getEmail().equals(guideEmail)) {
			throw new ForbiddenException();				
		}
		
	}
	
	private GuideCredential decodeToken (String tokenString) throws GuideAuthException {
		System.out.println(tokenString);
		try {
			int index = tokenString.indexOf(" ");
			tokenString = tokenString.substring(index + 1);
			byte[] byteDecode = Base64.getDecoder().decode(tokenString);
			String token = new String(byteDecode);
			String [] credentials = token.split(":");
			return new GuideCredential (credentials[0], credentials[1]);
		} catch (Exception e) {
			throw new GuideAuthException();
		}

}


}
