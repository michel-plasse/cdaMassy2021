package fr.cdamassy2021.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.QuestionService;

/**
 * 
 * @author Kamal, Ben, Vinoth, Thomas
 * 
 * */

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/questions/activite")
	public ModelAndView activiteQuestions(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("activitequestions");
		Personne currentUser = (Personne)request.getSession().getAttribute("currentUser");
		mav.addObject("allCanauxMembre",currentUser.getAllCanauxMembre());
		return mav;
	}
	
	@RequestMapping("/questions/afficher")
	public ModelAndView afficherQuestions() {
		List<Question> listQuestion = questionService.listAll();
		ModelAndView mav = new ModelAndView("questions");
		mav.addObject("allQuestions", listQuestion);
		return mav;
	}
	
	@RequestMapping("/questions/{idCanal}/afficher")
	public ModelAndView afficherQuestionsCanal(@PathVariable(value="idCanal") long idCanal, ModelAndView mv) {
		mv.setViewName("questions");
		List<Question> listQuestion = questionService.listByCanal(idCanal);
		mv.addObject("allQuestions", listQuestion);
		return mv;
	}
	
	@RequestMapping("/questions/creer")
	public ModelAndView creerQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("creerquestion");
		Personne currentUser = (Personne)request.getSession().getAttribute("currentUser");
		mav.addObject("allCanauxMembre",currentUser.getAllCanauxMembre());
		return mav;
	}
	
	@RequestMapping("/questions/repondre")
	public ModelAndView repondreQuestions(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("repondrequestions");
		long userId = ((Personne)request.getSession().getAttribute("currentUser")).getIdPersonne();
		long canalId = Long.parseLong(request.getParameter("canal"));
		mav.addObject("pendingQuestions", questionService.listPending(userId,canalId));
		return mav;
	}
}
