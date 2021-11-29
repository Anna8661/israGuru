package telran.java38.israGuru.user.controller;

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

import telran.java38.israGuru.guide.securityService.GuideSecurityService;
import telran.java38.israGuru.user.dto.GuideDto;
import telran.java38.israGuru.user.dto.GuideRegDto;
import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.get.GuideUpdateDto;
import telran.java38.israGuru.user.dto.get.GuideUpdatePasswordDto;
import telran.java38.israGuru.user.dto.get.UserRegDto;
import telran.java38.israGuru.user.dto.get.UserUpdateDto;
import telran.java38.israGuru.user.dto.get.UserUpdatePasswordDto;
import telran.java38.israGuru.user.service.UserServise;

@RestController 
@RequestMapping("/guide")
public class AccountGuideController {
	@Autowired
	UserServise guideServise;
	
	@Autowired
	GuideSecurityService guideSecurityService;
	
	@Autowired
	public AccountGuideController(UserServise	guideServise, GuideSecurityService guideSecurityService) {
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
// for user
	
	@PostMapping
	public UserDto registerUser (@RequestBody UserRegDto userRegDto) {
		return userServise.addNewUser(userRegDto);
	}
	
	//FIXME
	@PostMapping("/{login}")
	public UserDto loginUser(@PathVariable String login) {
		return userServise.findUserById(login);
	}
	
	@PutMapping("/{userId}")
	public UserDto updateUser(@PathVariable String userId, @RequestBody UserUpdateDto userUpdateDto, @RequestHeader ("Authorization") String token) throws AuthException {
		userSecurityService.updateUserValidate(userId, token);
		return userServise.updateUser(userId, userUpdateDto);
	}
	
	@PutMapping("password/{userId}")
	public boolean updateUserPassword (@PathVariable String userId, @RequestBody UserUpdatePasswordDto userUpdatePasswordDto, @RequestHeader ("Authorization") String token) throws AuthException {
		userSecurityService.updateUserValidate(userId, token);
		return userServise.updateUserPassword(userId, userUpdatePasswordDto);
	}
	
	@DeleteMapping("/{userId}")
	public boolean removeUser(@PathVariable String userId, @RequestHeader ("Authorization") String token) {
		userSecurityService.updateUserValidate(userId, token);
		return userServise.removeUser(userId);
	}

}
