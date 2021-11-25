package telran.java38.israGuru.guide.controller;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.GuideUpdatePasswordDto;
import telran.java38.israGuru.guide.securityService.GuideSecurityService;
import telran.java38.israGuru.guide.dto.GuideRegDto;
import telran.java38.israGuru.guide.service.GuideServise;

@RestController 
@RequestMapping("/guide")
public class AccountGuideController {
	@Autowired
	GuideServise guideServise;
	
	@Autowired
	GuideSecurityService guideSecurityService;
	
	@Autowired
	public AccountGuideController(GuideServise	guideServise, GuideSecurityService guideSecurityService) {
		this.guideServise = guideServise;
		this.guideSecurityService = guideSecurityService;
	}	
	
	@PostMapping
	public GuideDto registerGuide (@RequestBody GuideRegDto newGuide) {
		return guideServise.addNewGuide(newGuide);
	}
//	
//	//FIXME
//	@PostMapping("/{login}")
//	public GuideDto loginGuide (@PathVariable String login) {
//		return guideServise.findGuideById(login);
//	}
	
	@PutMapping("/{guideId}")
	public GuideDto updateGuide (@PathVariable String guideId, @RequestBody GuideUpdateDto guideUpdate, @RequestHeader ("Authorization") String token) throws AuthException {
		guideSecurityService.updateGuideValidate(guideId, token);
		return guideServise.updateGuide(guideId, guideUpdate);
	}
	
	@PutMapping("password/{guideId}")
	public boolean updateGuidePassword (@PathVariable String guideId, @RequestBody GuideUpdatePasswordDto guideUpdatePasswordDto, @RequestHeader ("Authorization") String token) throws AuthException {
		guideSecurityService.updateGuideValidate(guideId, token);
		return guideServise.updateGuidePassword(guideId, guideUpdatePasswordDto);
	}
	
	@GetMapping("/{guideId}")
	public GuideDto getGuideById (@PathVariable String guideId) {
		return guideServise.findGuideById(guideId);
	}
	
	@DeleteMapping("/{guideId}")
	public boolean removeGuide (@PathVariable String guideId, @RequestHeader ("Authorization") String token) {
		guideSecurityService.updateGuideValidate(guideId, token);
		return guideServise.removeGuide(guideId);
	}

}
