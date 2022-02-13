package fr.cdamassy2021.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.CanalService;
import fr.cdamassy2021.service.PersonneService;
import fr.cdamassy2021.service.QuestionService;

/**
 * 
 * @author Kamal, Ben, Vinoth, Thomas
 * 
 * */

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private CanalService canalService;
	
	@Autowired
	private PersonneService personneService;
	
	@RequestMapping("/questions/activite")
	public ModelAndView activiteQuestions(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("activitequestions");
		Personne currentUser = (Personne)request.getSession().getAttribute("currentUser");
		mav.addObject("allCanauxMembre",currentUser.getAllCanauxMembre());
		return mav;
	}
	
	@RequestMapping("/questions/afficher")
	public ModelAndView afficherQuestions() {
		List<Question> listQuestion = questionService.listAll();
		ModelAndView mav = new ModelAndView("questions");
		mav.addObject("allQuestions", listQuestion);
		return mav;
	}
	
	@RequestMapping("/questions/{idCanal}/afficher")
	public ModelAndView afficherQuestionsCanal(@PathVariable(value="idCanal") long idCanal, ModelAndView mv) {
		mv.setViewName("questions");
		List<Question> listQuestion = questionService.listByCanal(idCanal);
		mv.addObject("allQuestions", listQuestion);
		return mv;
	}
	
	@RequestMapping("/questions/creer")
	public ModelAndView creerQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("creerquestion");
		Personne currentUser = (Personne)request.getSession().getAttribute("currentUser");
		mav.addObject("allCanauxMembre",currentUser.getAllCanauxMembre());
		mav.addObject("messageSuccess",request.getParameter("messageSuccess"));
		mav.addObject("messageError",request.getParameter("messageError"));
		return mav;
	}
	
	@RequestMapping(value="/questions/enregistrer", method = RequestMethod.POST)
	public ModelAndView enregistrerQuestion(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attrs) {

        //recuperation et test d'acceptation des données
        //recupere libelle question
        String libelleQuestion = request.getParameter("libelleQuestion");
        System.out.println(libelleQuestion);
        //test acceptation:
        boolean isLegitQuestion = (libelleQuestion.length() <= 255
                && libelleQuestion.length() > 0) ? true : false;

        //recupere les libelles des propositions dans un tableau
        String[] allPropositions = request.getParameterValues("proposition");
        //test acceptation
        boolean isLegitPropositions = true;
        boolean isFreeAnswerTypeOfQuestion= false;
        if (allPropositions != null) {
            if (allPropositions.length > 1) {
                for (String proposition : allPropositions) {
                    if (proposition.length() > 255 || proposition.length() == 0) {
                        isLegitPropositions = false;
                        System.out.println(proposition);
                    }
                }
            }
            else{isFreeAnswerTypeOfQuestion = true;}

        }
        int canalSelectionne = Integer.parseInt(request.getParameter("canalChoisi"));
        //test de validité du formulaire
        boolean valide = true;

        if (!isLegitQuestion) {
            System.out.println("not legit question");
            attrs.addAttribute("messageError", "question null ou Trop de caracteres (max=255)");
            valide = false;
        }
        if (!isLegitPropositions) {
            System.out.println("not legit proposition");
        	attrs.addAttribute("messageError", "proposition null ou Trop de caracteres (max=255)");
            valide = false;
        }

        if (valide && !isFreeAnswerTypeOfQuestion) {
            //recupere la valeur de estCorrecte dans une liste.
            ArrayList<String> allCorrectnesses = new ArrayList<>();
            allCorrectnesses.add(request.getParameter("correctness"));
            for (int i = 2; i < allPropositions.length + 1; i++) {
                allCorrectnesses.add(request.getParameter("correctness" + i));
            }

            Personne auteur = (Personne) request.getSession().getAttribute("currentUser");
            questionService.creerQuestion(
                        libelleQuestion,
                        allPropositions,
                        allCorrectnesses,
                        auteur,
                        (long)canalSelectionne);
            System.out.println("valid multiple choice answer type");
        }

        else if (valide){
            Personne auteur = (Personne) request.getSession().getAttribute("currentUser");
            questionService.creerQuestion(
                        libelleQuestion,
                        auteur,
                        (long)canalSelectionne);    
            System.out.println("valid free answer type");
        }

        if(valide) {attrs.addAttribute("messageSuccess", "Question Enregistree");}
		ModelAndView mav = new ModelAndView("redirect:/questions/creer");
        System.out.println("should be ok Save");
		return mav;
	}
	
	@RequestMapping("/questions/repondre")
	public ModelAndView repondreQuestions(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("repondrequestions");
		long userId = ((Personne)request.getSession().getAttribute("currentUser")).getIdPersonne();
		Long canalId = (Long)request.getSession().getAttribute("currentCanalId");
		if (canalId == null) {
			canalId = Long.parseLong(request.getParameter("canal"));
			request.getSession().setAttribute("currentCanalId",canalId);
		}
		mav.addObject("pendingQuestions", questionService.listPending(userId,canalId));
		return mav;
	}
}
