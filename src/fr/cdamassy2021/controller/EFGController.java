package fr.cdamassy2021.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.service.EFGService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("canaux/{idCanal}/EFGs")
@RestController
public class EFGController {


	
	@Autowired
	private EFGService efgService;

	@RequestMapping
	public ModelAndView allEFGs(@PathVariable(value = "idCanal") int idCanal, ModelAndView mv) {

		Optional<List<EFG>> optEFGS = efgService.listByCanal(idCanal);
		if(optEFGS.isPresent()) {
			List<EFG> efgs = optEFGS.get() ;
			mv.setViewName("EFGs");
			mv.addObject("EFGs", efgs);
			mv.addObject("idCanal", idCanal);
			int nbMembres = efgService.nombreMembresCanal(idCanal);
			mv.addObject("nbMembres", nbMembres);
			return mv;
		}else {
			
			mv.setViewName("erreur");
			mv.addObject("message", "erreur ");
			return mv;
		}
		
		
	}

	/**
	 * @RequestMapping("canaux/{idCanal}/EFGs/{idEFG}")
	public ModelAndView oneEFG(@PathVariable(value = "idEFG") int idEFG, @PathVariable(value = "idCanal") int idCanal,
			ModelAndView mv) {
		mv.setViewName("EFG");
		EFG efg = efgService.findById(idEFG);
		mv.addObject("EFG", efg);
		return mv;
	}
	**/
	
	@RequestMapping("/{idEFG}")
	public ResponseEntity<EFG> getEFG(@PathVariable(value = "idEFG") int idEFG){
		Optional<EFG> optEFG = efgService.findById(idEFG);
		if(optEFG.isPresent()) {
			return ResponseEntity.ok(optEFG.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping("/new")
	public ModelAndView newEFG(@PathVariable(value="idCanal") int idCanal, ModelAndView mv) {
		mv.setViewName("createEFG");
		mv.addObject("newEFG", new EFG());
		return mv;
	}
	
	@PostMapping("/new") 
	public ModelAndView postForm(@ModelAttribute("newEFG") EFG efg, @PathVariable(value="idCanal") int idCanal) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("EFG", efg);
		efg.setIdCreateur(1);
		efg.setIdCanal(idCanal);
		EFG efgSaved = efgService.saveEFG(efg);
		System.out.println(efgSaved);
		mv.setViewName("redirect:/canaux/{idCanal}/EFGs/"+efgSaved.getIdEfg());
		return mv;
	}
}
