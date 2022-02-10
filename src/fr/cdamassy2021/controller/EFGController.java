package fr.cdamassy2021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EFGController {
	
	@RequestMapping("canaux/1/EFGs")
	public ModelAndView coucou(ModelAndView mv) {
		System.out.println("Coucou bitch");
		mv.setViewName("EFGs");
		return mv;
	}
}
