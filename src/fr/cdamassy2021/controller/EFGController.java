package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.service.EFGService;

@Controller
public class EFGController {
	
	@Autowired
	private EFGService efgService;
	
	@RequestMapping("canaux/{idCanal}/EFGs")
	public ModelAndView coucou(@PathVariable(value="idCanal") ModelAndView mv) {
		mv.setViewName("EFGs");
		List<EFG> efgs = efgService.listAll();
		mv.addObject("EFGs", efgs);
		return mv;
	}
}
