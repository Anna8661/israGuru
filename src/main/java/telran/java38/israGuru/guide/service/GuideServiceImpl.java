package telran.java38.israGuru.guide.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.guide.dao.GuideRepository;
import telran.java38.israGuru.guide.dao.GuideSecurityRepository;
import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.GuideUpdatePasswordDto;
import telran.java38.israGuru.guide.dto.GuideRegDto;
import telran.java38.israGuru.guide.dto.exception.GuideConflictException;
import telran.java38.israGuru.guide.dto.exception.GuideNotFoundException;
import telran.java38.israGuru.guide.model.Guide;
import telran.java38.israGuru.guide.model.GuideSecurity;


@Service
public class GuideServiceImpl implements GuideServise {
	
	@Autowired
	GuideRepository guideRepository;
	@Autowired
	GuideSecurityRepository guideSecurityRepository;
	ModelMapper modelMapper;
	

	public GuideServiceImpl(GuideRepository guideRepository, ModelMapper modelMapper, GuideSecurityRepository guideSecurityRepository) {
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
		Guide guide = modelMapper.map(newGuide, Guide.class);
		guide = guideRepository.save(guide);
		guideSecurityRepository.save(new GuideSecurity(newGuide.getEmail(), hashPassword));
		return modelMapper.map(guide, GuideDto.class);				
	}
	
	@Override
	public GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate) {
		Guide guide = guideRepository.findById(guideId).orElseThrow(()-> new GuideNotFoundException(guideId));
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
		guideSecurityRepository.save(new GuideSecurity(guideId, hashPassword));
		return true;
	}

	@Override
	public GuideDto findGuideById(String guideId) {
		Guide guide = guideRepository.findById(guideId).orElseThrow(()-> new GuideNotFoundException(guideId));
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
