package fr.cdamassy2021.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.cdamassy2021.dto.MbrGrpEFGDto;
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
	
	
	@RequestMapping("/api/MbrGrpEFG/{idEfg}")
	public List<Object> afficherMbrGrpEFGparidEfg(@PathVariable int idEfg) {			 
		return mbrGrpEFGService.queryEfgById(idEfg);
	}


}
