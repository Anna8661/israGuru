package telran.java38.israGuru.user.service;

import telran.java38.israGuru.user.dto.GuideDto;
import telran.java38.israGuru.user.dto.GuideRegDto;
import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.get.GuideUpdateDto;
import telran.java38.israGuru.user.dto.get.GuideUpdatePasswordDto;
import telran.java38.israGuru.user.dto.get.UserRegDto;
import telran.java38.israGuru.user.dto.get.UserUpdateDto;
import telran.java38.israGuru.user.dto.get.UserUpdatePasswordDto;

public interface UserServise {

	
	
	
// for guide
	GuideDto addNewGuide(GuideRegDto newGuide);

	GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate);

	GuideDto findGuideById(String guideId);

	boolean removeGuide(String guideId);

	boolean updateGuidePassword(String guideId, GuideUpdatePasswordDto guideUpdatePasswordDto);
	
//for user
	
	UserDto addNewUser(UserRegDto newUser);

	UserDto updateUser(String userId, UserUpdateDto userUpdateDto);

	UserDto findUserById(String userId);

	boolean removeUser (String userId);

	boolean updateUserPassword(String userId, UserUpdatePasswordDto userUpdatePasswordDto);


}
