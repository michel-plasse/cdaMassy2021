package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.QuestionService;


/**
 * 
 * @autor kamal
 * 
 * */

@Controller
public class QuestionnaireController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/questionnaires/afficher")
	public ModelAndView afficherQuestionnaires() {
		List<Question> listQuestionnaires = questionService.listAll();
		ModelAndView mav = new ModelAndView("questionnaires");
		
		mav.addObject("questionnaires", listQuestionnaires);
		return mav;
	}
}
