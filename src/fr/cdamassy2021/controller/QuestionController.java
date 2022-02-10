package fr.cdamassy2021.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.CanalService;
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
	
	@Autowired
	private CanalService canalService;
	
	@RequestMapping("/questions/afficher")
	public ModelAndView afficherQuestions() {
		List<Question> listQuestion = questionService.listAll();
		ModelAndView mav = new ModelAndView("questions");
		
		mav.addObject("questions", listQuestion);
		return mav;
	}
	
	@RequestMapping("/questions/creer")
	public ModelAndView creerQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		List<Canal> canauxMembre = canalService.listByMemberId(((Personne)request.getAttribute("currentUser")).getId());
		ModelAndView mav = new ModelAndView("creerquestion");
		
		/**mav.addObject("questions", listQuestion);*/
		return mav;
	}
}
