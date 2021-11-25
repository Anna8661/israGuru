package telran.java38.israGuru.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.user.dao.UserRepository;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.UserRegDto;
import telran.java38.israGuru.user.dto.UserUpdatePasswordDto;
import telran.java38.israGuru.user.dto.UserUpdateDto;
import telran.java38.israGuru.user.dto.exception.UserConflictException;
import telran.java38.israGuru.user.dto.exception.UserNotFoundException;
import telran.java38.israGuru.user.model.User;
import telran.java38.israGuru.user.model.UserSecurity;


@Service
public class UserServiceImpl implements UserServise {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserSecurityRepository userSecurityRepository;
	ModelMapper modelMapper;
	

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserSecurityRepository userSecurityRepository) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.userSecurityRepository = userSecurityRepository;
	}

	@Override
	public UserDto addNewUser(UserRegDto newUser) {
		if (userRepository.existsById(newUser.getEmail())) {
			throw new UserConflictException(newUser.getEmail());			
		}
		String hashPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());	
		User user = modelMapper.map(newUser, User.class);
		user = userRepository.save(user);
		userSecurityRepository.save(new UserSecurity(newUser.getEmail(), hashPassword));
		return modelMapper.map(user, UserDto.class);			
	}


	@Override
	public UserDto updateUser(String userId, UserUpdateDto userUpdateDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		if (userUpdateDto.getName() != null) {user.setName(userUpdateDto.getName());}
		if (userUpdateDto.getPhoneNumber() != null) {user.setPhoneNumber(userUpdateDto.getPhoneNumber());}
		user = userRepository.save(user);
		return modelMapper.map(user, UserDto.class);					
	}


	@Override
	public UserDto findUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		return modelMapper.map(user, UserDto.class);	
	}


	@Override
	public boolean removeUser(String userId) {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		userRepository.deleteById(userId);
		if (userRepository.existsById(userId)) {
			return false;
		}
		return true;
	}


	@Override
	public boolean updateUserPassword(String userId, UserUpdatePasswordDto userUpdatePasswordDto) {
		String hashPassword = BCrypt.hashpw(userUpdatePasswordDto.getPassword(), BCrypt.gensalt());	
		userSecurityRepository.save(new UserSecurity(userId, hashPassword));
		return true;
	}



	
}
