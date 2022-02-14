package fr.cdamassy2021.controller;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		mv.addObject("idCanal", IdCanal);

		return mv;
	}

	@PostMapping("/canaux/enleve")
	public String deleteMembre(HttpServletRequest request, HttpServletResponse response, Model model) {
		// v��rifier droit de l'utilisateur
		// v��rifier idCanal et idPersonne
		int idMembreAEffacer = Integer.parseInt(request.getParameter("idMembreAEffacer"));
		int idCanal = Integer.parseInt(request.getParameter("idCanal"));
		System.out.println("==============" + idMembreAEffacer + "++++++++" + idCanal);
		personneService.enleverMembreDuCanal(idMembreAEffacer, idCanal);
		model.addAttribute("idCanal", idCanal);
		return "redirect:/canaux/{idCanal}";
	}
	@RequestMapping("/canaux/addMembre")
	public String addMembreToCanal(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		int idPersonneAAjouter = Integer.parseInt(request.getParameter("idPersonneAjouter"));
		int idCanal = Integer.parseInt(request.getParameter("idCanalAjouter"));
		 personneService.ajouterMembreDuCanal(idPersonneAAjouter, idCanal);
		 model.addAttribute("idCanal", idCanal);
		return "redirect:/canaux/{idCanal}";
	}

}
