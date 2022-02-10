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
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/questions/afficher")
	public ModelAndView afficherQuestions() {
		List<Question> listQuestion = questionService.listAll();
		ModelAndView mav = new ModelAndView("questions");
		
		mav.addObject("questions", listQuestion);
		return mav;
	}
	
	@RequestMapping("/questions/creer")
	public ModelAndView creerQuestion() {
		List<Question> listQuestion = questionService.listAll();
		ModelAndView mav = new ModelAndView("creerquestion");
		
		/**mav.addObject("questions", listQuestion);*/
		return mav;
	}
}
