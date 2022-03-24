package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.service.CanalService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/canaux")
public class CanalRestController {
	
	@Autowired
	CanalService canalService;
	
	/*
	 * GET /api/canaux/{idLogin} -> renvoie tous les canaux dont l'utilisateur fait partie
	 */
	@RequestMapping(value="/{idLogin}", method = RequestMethod.GET)
	public List<Canal> canauxByIdLogin(@PathVariable int idLogin){
		return canalService.ListCanauxByIdMembre(idLogin);
	}

}
