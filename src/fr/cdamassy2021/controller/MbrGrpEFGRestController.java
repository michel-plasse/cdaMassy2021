package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.entity.MbrGrpEFG;
import fr.cdamassy2021.service.MbrGrpEFGService;

@RestController
public class MbrGrpEFGRestController {
	
	@Autowired
	MbrGrpEFGService mbrGrpEFGService;
	
	@RequestMapping("/api/MbrGrpEFG")
	public Iterable<MbrGrpEFG> testAffichage() {	
		return mbrGrpEFGService.findAll();
	}
	
	@RequestMapping("/m1")
	public List<MbrGrpEFG> fonctionRestController() {	
		return mbrGrpEFGService.fonctionService();
	}

}
