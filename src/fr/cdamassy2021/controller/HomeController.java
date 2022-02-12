package fr.cdamassy2021.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.service.PersonneService;


@Controller
public class HomeController {
	@Autowired
	private PersonneService personneService;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
		

	@RequestMapping("/connexion")
	public ModelAndView connexion() {
		ModelAndView mav = new ModelAndView("connexion");
		return mav;
	}
	@RequestMapping("/tryconnection")
	public ModelAndView tryConnexion(HttpServletRequest request,
			HttpServletResponse response) {
		String email=request.getParameter("login");  
		String password=request.getParameter("pwd");
		String message;
		Personne currentUser = personneService.exist(email,password);
		if(currentUser!=null){
			message = "Welcome " + email + ".";
			ModelAndView mav = new ModelAndView("welcome", 
					"message", message);
			mav.addObject("currentUser", currentUser);
			return mav;  

		}else{
			message = "Wrong username or password.";
			return new ModelAndView("errorPage", 
					"message", message);
		}
	}
}
