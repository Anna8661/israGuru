package telran.java38.israGuru.guide.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import telran.java38.israGuru.guide.dao.GuideRepository;
import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.NewGuideDto;
import telran.java38.israGuru.guide.dto.exception.GuideConflictException;
import telran.java38.israGuru.guide.dto.exception.GuideNotFoundException;
import telran.java38.israGuru.guide.model.Guide;


@Service
public class GuideServiceImpl implements GuideServise {
	
	GuideRepository guideRepository;
	ModelMapper modelMapper;
	
	

	public GuideServiceImpl(GuideRepository guideRepository, ModelMapper modelMapper) {
		this.guideRepository = guideRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public GuideDto addNewGuide(NewGuideDto newGuide) {
		if (guideRepository.existsById(newGuide.getGuideId())) {
			throw new GuideConflictException(newGuide.getGuideId());			
		}
		Guide guide = modelMapper.map(newGuide, Guide.class);
		guide = guideRepository.save(guide);
		return modelMapper.map(guide, GuideDto.class);		
		
	}
	
	@Override
	public GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate) {
		Guide guide = guideRepository.findById(guideUpdate.getGuideId()).orElseThrow(()-> new GuideNotFoundException(guideUpdate.getGuideId()));
		if (guideUpdate.getEmail() != null) {guide.setEmail(guideUpdate.getEmail());}
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
