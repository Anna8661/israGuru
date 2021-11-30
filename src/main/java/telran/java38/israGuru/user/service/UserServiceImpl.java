package telran.java38.israGuru.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.user.dao.UserRepository;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.dto.GuideDto;
import telran.java38.israGuru.user.dto.UserDto;
import telran.java38.israGuru.user.dto.exception.UserConflictException;
import telran.java38.israGuru.user.dto.exception.UserNotFoundException;
import telran.java38.israGuru.user.dto.get.GuideRegDto;
import telran.java38.israGuru.user.dto.get.GuideUpdateDto;
import telran.java38.israGuru.user.dto.get.GuideUpdatePasswordDto;
import telran.java38.israGuru.user.dto.get.UserRegDto;
import telran.java38.israGuru.user.dto.get.UserUpdateDto;
import telran.java38.israGuru.user.dto.get.UserUpdatePasswordDto;
import telran.java38.israGuru.user.model.User;
import telran.java38.israGuru.user.model.UserSecurity;


@Service
public class UserServiceImpl implements UserServise {
	
	UserRepository userRepository;
	UserSecurityRepository userSecurityRepository;
	ModelMapper modelMapper;
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserSecurityRepository userSecurityRepository,
			ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userSecurityRepository = userSecurityRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
//for guide

	@Override
	public GuideDto addNewGuide(GuideRegDto newGuide) {
		if (userRepository.existsById(newGuide.getEmail())) {
			throw new UserConflictException("Guide " + newGuide.getEmail());			
		}
		String hashPassword = passwordEncoder.encode(newGuide.getPassword());		
		User guide = modelMapper.map(newGuide, User.class);
		guide = userRepository.save(guide);
		userSecurityRepository.save(new UserSecurity(newGuide.getEmail(), hashPassword));
		return modelMapper.map(guide, GuideDto.class);				
	}
	
	@Override
	public GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate) {
		User guide = userRepository.findById(guideId).orElseThrow(()-> new UserNotFoundException("Guide " + guideId));
		if (guideUpdate.getFirstName() != null) {guide.setFirstName(guideUpdate.getFirstName());}
		if (guideUpdate.getLastName() != null) {guide.setLastName(guideUpdate.getLastName());}
		if (guideUpdate.getImage() != null) {guide.setImage(guideUpdate.getImage());}
		if (guideUpdate.getPhoneNumbers() != null) {guide.setPhoneNumbers(guideUpdate.getPhoneNumbers());}
		if (guideUpdate.getLicense() != null) {guide.setLicense(guideUpdate.getLicense());}
		if (guideUpdate.getDescription() != null) {guide.setDescription(guideUpdate.getDescription());}
		if (guideUpdate.getAdditionalDescription() != null) {guide.setAdditionalDescription(guideUpdate.getAdditionalDescription());}
		if (guideUpdate.getSocialMedia() != null) {guide.setSocialMedia(guideUpdate.getSocialMedia());}
		guide = userRepository.save(guide);			
		return modelMapper.map(guide, GuideDto.class);		
		
	}
	
	@Override
	public boolean updateGuidePassword(String guideId, GuideUpdatePasswordDto guideUpdatePasswordDto) {
		if (guideUpdatePasswordDto.getPassword() == null) {
			return false;			
		}
		String hashPassword = passwordEncoder.encode(guideUpdatePasswordDto.getPassword());		
		userSecurityRepository.save(new UserSecurity(guideId, hashPassword));
		return true;
	}

	@Override
	public GuideDto findGuideById(String guideId) {
		User guide = userRepository.findById(guideId).orElseThrow(()-> new UserNotFoundException("Guide " + guideId));
		return modelMapper.map(guide, GuideDto.class);	
	}

	@Override
	public boolean removeGuide(String guideId) {
		if (!userRepository.existsById(guideId)) {
			throw new UserNotFoundException("Guide " + guideId);
		}
		userRepository.deleteById(guideId);
		userSecurityRepository.deleteById(guideId);
		if (!userRepository.existsById(guideId)) {
			return true;
		}
		return false;		
	}
	
//for user

	@Override
	public UserDto addNewUser(UserRegDto newUser) {
		if (userRepository.existsById(newUser.getEmail())) {
			throw new UserConflictException(newUser.getEmail());			
		}
		String hashPassword = passwordEncoder.encode(newUser.getPassword());
		User user = modelMapper.map(newUser, User.class);
		user = userRepository.save(user);
		userSecurityRepository.save(new UserSecurity(newUser.getEmail(), hashPassword));
		return modelMapper.map(user, UserDto.class);	
	}

	@Override
	public UserDto updateUser(String userId, UserUpdateDto userUpdateDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		if (userUpdateDto.getFirstName() != null) {user.setFirstName(userUpdateDto.getFirstName());}
		if (userUpdateDto.getLastName() != null) {user.setLastName(userUpdateDto.getLastName());}
		if (userUpdateDto.getPhoneNumbers() != null) {user.setPhoneNumbers(userUpdateDto.getPhoneNumbers());}
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
		userSecurityRepository.deleteById(userId);
		if (userRepository.existsById(userId)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserPassword(String userId, UserUpdatePasswordDto userUpdatePasswordDto) {
			String hashPassword = passwordEncoder.encode(userUpdatePasswordDto.getPassword());
			userSecurityRepository.save(new UserSecurity(userId, hashPassword));
			return true;
	}

	@Override
	public GuideDto findVerifiedGuideById(String guideId) {
		User guide = userRepository.findById(guideId).orElseThrow(()-> new UserNotFoundException("Guide " + guideId));
		if (!guide.getRoles().contains("VERIFIEDGUIDE")) {
			throw new UserNotFoundException("VERIFIED GUIDE " + guideId);			
		}
		return modelMapper.map(guide, GuideDto.class);	

	}
	
	
}

