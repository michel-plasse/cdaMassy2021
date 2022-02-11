package fr.cdamassy2021.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/canaux/{idCanal}")
	public ModelAndView listMembre(@PathVariable("idCanal") int IdCanal) {
		Collection<Personne> collectionPersonnes = personneService.listMembreByCanal(IdCanal);
		ModelAndView mv = new ModelAndView("membres");
		mv.addObject("membres", collectionPersonnes);
		return mv;
	}
	


}
