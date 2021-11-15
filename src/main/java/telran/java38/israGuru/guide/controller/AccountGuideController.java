package telran.java38.israGuru.guide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.israGuru.guide.dto.GuideDto;
import telran.java38.israGuru.guide.dto.GuideUpdateDto;
import telran.java38.israGuru.guide.dto.NewGuideDto;
import telran.java38.israGuru.guide.service.GuideServise;

@RestController 
@RequestMapping("/guide")
public class AccountGuideController {
	
	GuideServise guideServise;
	
	@Autowired
	public AccountGuideController(GuideServise	guideServise) {
		this.guideServise = guideServise;
	}	
	
	@PostMapping
	public GuideDto registerGuide (@RequestBody NewGuideDto newGuide) {
		return guideServise.addNewGuide(newGuide);
	}
	
	@PutMapping("/{guideId}")
	public GuideDto updateGuide (@PathVariable String guideId, @RequestBody GuideUpdateDto guideUpdate) {
		return guideServise.updateGuide(guideId, guideUpdate);
	}
	
	@GetMapping("/{guideId}")
	public GuideDto getGuideId (@PathVariable String guideId) {
		return guideServise.findGuideById(guideId);
	}
	
	@DeleteMapping("/{guideId}")
	public boolean removeGuide (@PathVariable String guideId) {
		return guideServise.removeGuide(guideId);
	}

}
