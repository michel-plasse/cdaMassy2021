package fr.cdamassy2021.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.service.CanalService;
import fr.cdamassy2021.service.PersonneService;

@Controller
public class PersonneController {

	@Autowired
	PersonneService personneService;
	
	@Autowired
	CanalService canalService;

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
		Canal canal = new Canal();
		mv.addObject("canal", canal);
		System.out.println("===========================" + canal.getNomCanal());
		
		return mv;
	}
	
	@PostMapping("canaux/{canal.idCanal}/{idPersonne}/enleve")
	public String supprimerMembreDuCanal(
			@PathVariable("idCanal") int idCanal,
			@PathVariable("idPersonne") int idPersonne) {
		System.out.println("=================coucou");
		//v¨¦rifier droit de l'utilisateur
		//v¨¦rifier idCanal et idPersonne
		personneService.enleverMembreDuCanal(idCanal, idPersonne);
		return "redirect:/canaux/{idCanal}";
	}


}
