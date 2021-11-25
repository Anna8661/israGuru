package telran.java38.israGuru.user.service;

import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.UserRegDto;
import telran.java38.israGuru.user.dto.UserUpdatePasswordDto;
import telran.java38.israGuru.user.dto.UserUpdateDto;

public interface UserServise {

	UserDto addNewUser(UserRegDto newUser);

	UserDto updateUser(String userId, UserUpdateDto userUpdateDto);

	UserDto findUserById(String userId);

	boolean removeUser (String userId);

	boolean updateUserPassword(String userId, UserUpdatePasswordDto userUpdatePasswordDto);

}
