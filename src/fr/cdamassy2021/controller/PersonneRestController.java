package fr.cdamassy2021.controller;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.dto.MembreDto;
import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.service.CanalService;
import fr.cdamassy2021.service.PersonneService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/canal")
public class PersonneRestController {

	@Autowired
	PersonneService personneService;

	@Autowired
	CanalService canalService;
	
	/**
	 * GET /api/canal -> renvoie la liste des personnes
	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public List<MembreDto> GetAllMembres(){
//		ArrayList<MembreDto> allMembreDtos = new ArrayList<>();
//		Collection<Personne> allPersonnes = personneService.listAll();
//		allPersonnes.forEach(p -> allMembreDtos.add(new MembreDto(p)));
//		return allMembreDtos;
//	}

	/**
	 * GET /api/canal/{idCanal} -> renvoie la liste des membres d'un canal
	 */
	@RequestMapping(value="/{idCanal}", method = RequestMethod.GET)
	public List<MembreDto> GetMembresDuCanal(@PathVariable int idCanal){
		ArrayList<MembreDto> membreDtos = new ArrayList<>();
		Collection<Personne> personnesDuCanal = personneService.listMembreByCanal(idCanal);
		personnesDuCanal.forEach(p -> membreDtos.add(new MembreDto(p)));
		return membreDtos;
	}
	
	@RequestMapping(value="/{idCanal}/membre/{idMembre}")
	public ResponseEntity<Boolean> deleteMembre(
			@PathVariable int idCanal,
			@PathVariable int idMembre){
		try {
			personneService.enleverMembreDuCanal(idMembre, idCanal);
			return ResponseEntity.ok(true);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
//
//	@PostMapping("/canaux/enleve")
//	public String deleteMembre(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// verifier droit de l'utilisateur
//		// verifier idCanal et idPersonne
//		int idMembreAEffacer = Integer.parseInt(request.getParameter("idMembreAEffacer"));
//		int idCanal = Integer.parseInt(request.getParameter("idCanal"));
//
//		System.out.println("==============" + idMembreAEffacer + "++++++++" + idCanal);
//
//		Personne personne_en_session = (Personne) request.getSession().getAttribute("currentUser");
//
//		if(personne_en_session.getEst_gestionnaire()== 1) {
//			personneService.enleverMembreDuCanal(idMembreAEffacer, idCanal);
//			model.addAttribute("idCanal", idCanal);
//			return "redirect:/canaux/{idCanal}";
//
//		}else {
//			model.addAttribute("message", "Vous n'avez pas ces droits");
//			return "redirect:/erreur/{message}";
//		}
//
//	}
	
//	@RequestMapping("/canaux/addMembre")
//	public String addMembreToCanal(HttpServletRequest request, HttpServletResponse response, Model model) {
//		int idPersonneAAjouter = Integer.parseInt(request.getParameter("idPersonneAjouter"));
//		int idCanal = Integer.parseInt(request.getParameter("idCanalAjouter"));
//
//		Personne personne_en_session = (Personne) request.getSession().getAttribute("currentUser");
//
//		if(personne_en_session.getEst_gestionnaire() ==1) {
//			personneService.ajouterMembreDuCanal(idPersonneAAjouter, idCanal);
//			model.addAttribute("idCanal", idCanal);
//			return "redirect:/canaux/{idCanal}";
//
//		}else {
//			model.addAttribute("message", "Vous n'avez pas ces droits");
//			return "redirect:/erreur/{message}";
//		}
//	}

}
