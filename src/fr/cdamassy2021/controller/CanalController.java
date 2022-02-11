package fr.cdamassy2021.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.service.CanalService;
import fr.cdamassy2021.service.PersonneService;

@Controller
public class CanalController {

	@Autowired
	private CanalService canalService;

	@Autowired
	private PersonneService personneService;

	// autowired cree une instance de customerService. il reccupere le context dans
	// le fichier WebAppInitaialiser
	// cela cree aussi une instance de customerRepository
	@RequestMapping("/canaux")
	public ModelAndView home() {
		List<Canal> listCanaux = canalService.listAll();
		ModelAndView mav = new ModelAndView("canaux");
		mav.addObject("canaux", listCanaux);
		return mav;
	}


}
