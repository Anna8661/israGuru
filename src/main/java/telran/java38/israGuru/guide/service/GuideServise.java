package telran.java38.israGuru.guide.service;

import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.GuideUpdatePasswordDto;
import telran.java38.israGuru.guide.dto.GuideRegDto;

public interface GuideServise {

	GuideDto addNewGuide(GuideRegDto newGuide);

	GuideDto updateGuide(String guideId, GuideUpdateDto guideUpdate);

	GuideDto findGuideById(String guideId);

	boolean removeGuide(String guideId);

	boolean updateGuidePassword(String guideId, GuideUpdatePasswordDto guideUpdatePasswordDto);

}
