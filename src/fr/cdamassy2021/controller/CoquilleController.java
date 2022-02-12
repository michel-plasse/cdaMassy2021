package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Questionnaire;
import fr.cdamassy2021.service.QuestionnaireService;


/**
 * 
 * @author: grp2 11/02/2022, 12/02/2022
 * 
 * */

@Controller
public class CoquilleController {
	
	@RequestMapping("/coquille")
	public String afficherCoquille() {	
		return "coquille";
		
	}
					
}
