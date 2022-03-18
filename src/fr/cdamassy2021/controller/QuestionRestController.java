package fr.cdamassy2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionRestController {

	@Autowired
	private QuestionService questionService;
	
	/*
	 * GET /bycanal/{canalId} -> renvoie la liste des questions appartenant au canal
	 */
	@CrossOrigin
	@RequestMapping(value="/bycanal/{canalId}", method = RequestMethod.GET)
	public List<Question> getQuestionByCanal(@PathVariable int canalId){
		return questionService.listByCanal(canalId);
	}
	
	/*
	 * GET /api/product -> renvoie la liste de toutes les questions
	 */
    @CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET,  produces = "application/json")
	public List<Question> getQuestions(){
		return questionService.listAll();
	}
}
