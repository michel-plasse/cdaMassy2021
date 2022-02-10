package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
