package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.service.PersonneService;

@Controller
public class PersonneController {

	@Autowired
	PersonneService personneService;

	@RequestMapping("/personne")
	public ModelAndView personne() {
		List<Personne> listPersonne = personneService.listAll();
		ModelAndView mv = new ModelAndView("personne");
		mv.addObject("listPersonne", listPersonne);
		return mv;
	}

	@RequestMapping("/membres")
	public ModelAndView listMembre() {
		List<Personne> listPersonne = personneService.listAll();
		ModelAndView mv = new ModelAndView("membres");
		mv.addObject("membres", listPersonne);
		return mv;
	}

}
