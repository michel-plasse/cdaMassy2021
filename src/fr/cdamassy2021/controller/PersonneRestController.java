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

	/**
	 * GET /api/canal/{idCanal}/membre/{idMembre} -> supprimer un membre d'un canal 
	 */
	@RequestMapping(value="/{idCanal}/membre/{idMembre}", method = RequestMethod.GET)
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
	
	/**
	 * Post /api/canal/{idCanal}/membre/{idMembre} -> ajouter un membre a un canal 
	 */
	@RequestMapping(value="/{idCanal}/membre/{idMembre}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addMembre(
			@PathVariable int idCanal,
			@PathVariable int idMembre){
		try {
			personneService.ajouterMembreDuCanal(idMembre, idCanal);
			return ResponseEntity.ok(true);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
