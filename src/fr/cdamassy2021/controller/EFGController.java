package fr.cdamassy2021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EFGController {
	
	@RequestMapping("canaux/{idCanal}/EFGs")
	public ModelAndView coucou(@PathVariable(value="idCanal") ModelAndView mv) {
		mv.setViewName("EFGs");
		return mv;
	}
}
