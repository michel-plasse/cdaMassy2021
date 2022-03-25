package fr.cdamassy2021.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.entity.Reponse;
import fr.cdamassy2021.service.QuestionService;
import fr.cdamassy2021.service.ReponseService;

@Controller
public class ReponseController {
	@Autowired
	QuestionService questionService;
	@Autowired
	ReponseService reponseService;

	@RequestMapping("/reponses/envoyer")
	public ModelAndView envoyerReponse(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttrs) {
		//retrieve data:
		Personne currentUser = (Personne)request.getSession().getAttribute("currentUser");
		long questionId = Long.parseLong(request.getParameter("question"));
		Question question = questionService.findById(questionId);
		String libelleReponse = request.getParameter("reponse");
		//tryData:
		boolean valid = 
				(currentUser!=null 
				&& libelleReponse!=""
				&& libelleReponse!=null 
				&& question!=null)
				?true:false; 

		//valid true:
		if (valid) {
			Reponse reponse = new Reponse(
					currentUser,
					question,
					libelleReponse);
			// persist data
			reponseService.save(reponse);
			// successMsg		  
			redirectAttrs.addFlashAttribute("messageSuccess", "Votre réponse a été prise en compte.");
		}
		//valid false:
		else {
			redirectAttrs.addFlashAttribute("messageError", "Erreur: Votre réponse n'a pu être enregistrée.");
		}

		ModelAndView mav = new ModelAndView("redirect:/questions/repondre");
		return mav;
	}
}
