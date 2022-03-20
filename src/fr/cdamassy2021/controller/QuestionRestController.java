package fr.cdamassy2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.dto.QuestionDto;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.service.QuestionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/question")
public class QuestionRestController {

	@Autowired
	private QuestionService questionService;
	/*
	 * GET /api/question -> renvoie la liste de toutes les questions
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<QuestionDto> getQuestions(){
		ArrayList<QuestionDto> allQuestionDto = new ArrayList<QuestionDto>();
		questionService.listAll().forEach(q -> allQuestionDto.add(new QuestionDto(q)));
		return allQuestionDto;
	}
	/*
	 * GET /bycanal/{canalId} -> renvoie la liste des questions appartenant au canal
	 */
	@RequestMapping(value="/bycanal/{canalId}", method = RequestMethod.GET)
	public List<QuestionDto> getQuestionByCanal(@PathVariable int canalId){
		ArrayList<QuestionDto> allQuestionDto = new ArrayList<QuestionDto>();
		questionService.listByCanal(canalId).forEach(q -> allQuestionDto.add(new QuestionDto(q)));
		return allQuestionDto;
	}
	

}
