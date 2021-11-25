package telran.java38.israGuru.user.controller;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.UserRegDto;
import telran.java38.israGuru.user.dto.UserUpdateDto;
import telran.java38.israGuru.user.dto.UserUpdatePasswordDto;
import telran.java38.israGuru.user.securityService.UserSecurityService;
import telran.java38.israGuru.user.service.UserServise;

@RestController 
@RequestMapping("/user")
public class AccountUserController {
	@Autowired	
	UserServise userServise;
	
	@Autowired
	UserSecurityService userSecurityService;
	
	@Autowired
	public AccountUserController(UserServise userServise, UserSecurityService userSecurityService) {
		this.userServise = userServise;
		this.userSecurityService = userSecurityService;
	}	
	
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
