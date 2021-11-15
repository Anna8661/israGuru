package telran.java38.israGuru.guide.service;

import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.NewGuideDto;

public interface GuideServise {

	GuideDto addNewGuide(NewGuideDto newGuide);

	GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate);

	GuideDto findGuideById(String guideId);

	boolean removeGuide(String guideId);

}
