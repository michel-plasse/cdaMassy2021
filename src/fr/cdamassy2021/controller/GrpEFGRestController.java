package fr.cdamassy2021.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.entity.GrpEFG;
import fr.cdamassy2021.repository.GrpEFGService;

@RestController
public class GrpEFGRestController {
	
	@Autowired
	GrpEFGService grpEFGService;
	
	@RequestMapping(value="/api/GrpEFGs")	
	Iterable<GrpEFG> afficherTous() {
		return grpEFGService.findAll();
	}
	
	
}