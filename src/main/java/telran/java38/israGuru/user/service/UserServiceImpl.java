package telran.java38.israGuru.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.User.model.User;
import telran.java38.israGuru.User.model.UserSecurity;
import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.GuideUpdatePasswordDto;
import telran.java38.israGuru.guide.dto.UserDto;
import telran.java38.israGuru.guide.dto.UserRegDto;
import telran.java38.israGuru.guide.dto.UserUpdateDto;
import telran.java38.israGuru.guide.dto.UserUpdatePasswordDto;
import telran.java38.israGuru.guide.dto.GuideRegDto;
import telran.java38.israGuru.guide.dto.exception.GuideConflictException;
import telran.java38.israGuru.guide.dto.exception.GuideNotFoundException;
import telran.java38.israGuru.user.dao.UserRepository;
import telran.java38.israGuru.user.dao.UserSecurityRepository;
import telran.java38.israGuru.user.dto.exception.UserConflictException;
import telran.java38.israGuru.user.dto.exception.UserNotFoundException;


@Service
public class UserServiceImpl implements GuideServise {
	
	@Autowired
	UserRepository guideRepository;
	@Autowired
	UserSecurityRepository guideSecurityRepository;
	ModelMapper modelMapper;
	

	public UserServiceImpl(UserRepository guideRepository, ModelMapper modelMapper, UserSecurityRepository guideSecurityRepository) {
		this.guideRepository = guideRepository;
		this.modelMapper = modelMapper;
		this.guideSecurityRepository = guideSecurityRepository;
	}


	@Override
	public GuideDto addNewGuide(GuideRegDto newGuide) {
		if (guideRepository.existsById(newGuide.getEmail())) {
			throw new GuideConflictException(newGuide.getEmail());			
		}
		String hashPassword = BCrypt.hashpw(newGuide.getPassword(), BCrypt.gensalt());		
		User guide = modelMapper.map(newGuide, User.class);
		guide = guideRepository.save(guide);
		guideSecurityRepository.save(new UserSecurity(newGuide.getEmail(), hashPassword));
		return modelMapper.map(guide, GuideDto.class);				
	}
	
	@Override
	public GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate) {
		User guide = guideRepository.findById(guideId).orElseThrow(()-> new GuideNotFoundException(guideId));
		if (guideUpdate.getFirstName() != null) {guide.setFirstName(guideUpdate.getFirstName());}
		if (guideUpdate.getLastName() != null) {guide.setLastName(guideUpdate.getLastName());}
		if (guideUpdate.getImage() != null) {guide.setImage(guideUpdate.getImage());}
		if (guideUpdate.getPhoneNumbers() != null) {guide.setPhoneNumbers(guideUpdate.getPhoneNumbers());}
		if (guideUpdate.getLicense() != null) {guide.setLicense(guideUpdate.getLicense());}
		if (guideUpdate.getDescription() != null) {guide.setDescription(guideUpdate.getDescription());}
		if (guideUpdate.getAdditionalDescription() != null) {guide.setAdditionalDescription(guideUpdate.getAdditionalDescription());}
		if (guideUpdate.getSocialMedia() != null) {guide.setSocialMedia(guideUpdate.getSocialMedia());}
		guide = guideRepository.save(guide);
			
		return modelMapper.map(guide, GuideDto.class);		
		
	}
	
	@Override
	public boolean updateGuidePassword(String guideId, GuideUpdatePasswordDto guideUpdatePasswordDto) {
		if (guideUpdatePasswordDto.getPassword() == null) {
			return false;			
		}
		String hashPassword = BCrypt.hashpw(guideUpdatePasswordDto.getPassword(), BCrypt.gensalt());	
		guideSecurityRepository.save(new UserSecurity(guideId, hashPassword));
		return true;
	}

	@Override
	public GuideDto findGuideById(String guideId) {
		User guide = guideRepository.findById(guideId).orElseThrow(()-> new GuideNotFoundException(guideId));
		return modelMapper.map(guide, GuideDto.class);	
	}

	@Override
	public boolean removeGuide(String guideId) {
		if (!guideRepository.existsById(guideId)) {
			throw new GuideNotFoundException(guideId);
		}
		guideRepository.deleteById(guideId);
		if (!guideRepository.existsById(guideId)) {
			return true;
		}
		return false;
		
	}



	
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

