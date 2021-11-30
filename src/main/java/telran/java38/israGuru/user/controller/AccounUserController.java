package telran.java38.israGuru.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.israGuru.user.dto.GuideDto;
import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.get.GuideRegDto;
import telran.java38.israGuru.user.dto.get.GuideUpdateDto;
import telran.java38.israGuru.user.dto.get.GuideUpdatePasswordDto;
import telran.java38.israGuru.user.dto.get.UserRegDto;
import telran.java38.israGuru.user.dto.get.UserUpdateDto;
import telran.java38.israGuru.user.dto.get.UserUpdatePasswordDto;
import telran.java38.israGuru.user.service.UserServise;

@RestController 
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccounUserController {
	
	UserServise userServise;	
	
	@Autowired
	public AccounUserController(UserServise	userServise) {
		this.userServise = userServise;
	}	
	
	@PostMapping("/guide")
	public GuideDto registerGuide (@RequestBody GuideRegDto newGuide) {
		return userServise.addNewGuide(newGuide);
	}
	@PostMapping("/login/guide/{login}")
	public GuideDto loginGuide (Principal principal) {
		return userServise.findGuideById(principal.getName());
	}
	
	@PutMapping("/guide/{guideId}")
	public GuideDto updateGuide (@PathVariable String guideId, @RequestBody GuideUpdateDto guideUpdate) {
		return userServise.updateGuide(guideId, guideUpdate);
	}
	
	@PutMapping("/guide/password/{guideId}")
	public boolean updateGuidePassword (@PathVariable String guideId, @RequestBody GuideUpdatePasswordDto guideUpdatePasswordDto){
		return userServise.updateGuidePassword(guideId, guideUpdatePasswordDto);
	}
		
	@DeleteMapping("/guide/{guideId}")
	public boolean removeGuide (@PathVariable String guideId) {
		return userServise.removeGuide(guideId);
	}
// for user
	
	@GetMapping("/guide/{guideId}")
	public GuideDto getGuideById (@PathVariable String guideId) {
		return userServise.findVerifiedGuideById(guideId);
	}
	
	@PostMapping("/user")
	public UserDto registerUser (@RequestBody UserRegDto userRegDto) {
		return userServise.addNewUser(userRegDto);
	}
	
	@PostMapping("/login/user/{login}")
	public UserDto loginUser(Principal principal) {
		return userServise.findUserById(principal.getName());
	}
	
	@PutMapping("/user/{userId}")
	public UserDto updateUser(@PathVariable String userId, @RequestBody UserUpdateDto userUpdateDto){
		return userServise.updateUser(userId, userUpdateDto);
	}
	
	@PutMapping("/user/password/{userId}")
	public boolean updateUserPassword (@PathVariable String userId, @RequestBody UserUpdatePasswordDto userUpdatePasswordDto){
		return userServise.updateUserPassword(userId, userUpdatePasswordDto);
	}
	
	@DeleteMapping("/user/{userId}")
	public boolean removeUser(@PathVariable String userId) {
		return userServise.removeUser(userId);
	}

}
