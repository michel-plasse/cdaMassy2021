package fr.cdamassy2021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.dto.QuestionDto;
import fr.cdamassy2021.dto.ReponseDto;
import fr.cdamassy2021.entity.Reponse;
import fr.cdamassy2021.service.QuestionService;
import fr.cdamassy2021.service.ReponseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/question")
public class QuestionRestController {

	@Autowired
	private QuestionService questionService;

	@Autowired 
	private ReponseService reponseService;
	/*
	 * GET: /bycanal/{canalId} -> renvoie la liste des questions appartenant au canal
	 */
	@RequestMapping(value="/bycanal/{canalId}", method = RequestMethod.GET)
	public List<QuestionDto> getQuestionByCanal(@PathVariable int canalId){
		ArrayList<QuestionDto> allQuestionDto = new ArrayList<QuestionDto>();
		questionService.listByCanal(canalId).forEach(
				q -> allQuestionDto.add(new QuestionDto(q)));
		return allQuestionDto;
	}
	
	/*
	 * POST: /api/question/reponse -> renvoie reponse crée
	 */
	@RequestMapping(value="/reponse",
					method = RequestMethod.POST, 
					consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ReponseDto> saveReponse(@RequestBody ReponseDto reponseDto){
		try {
			Reponse r = reponseService.save(reponseDto.getIdAuteur(),
								reponseDto.getIdQuestion(),
								reponseDto.getLibelle());
			ReponseDto reponse = new ReponseDto(r);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/*
	 * GET: /api/question -> renvoie la liste de toutes les questions
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<QuestionDto> getQuestions(){
		ArrayList<QuestionDto> allQuestionDto = new ArrayList<QuestionDto>();
		questionService.listAll().forEach(q -> allQuestionDto.add(new QuestionDto(q)));
		return allQuestionDto;
	}
	
	/*
	 * POST: /api/question -> enregistre une nouvelle question à partir de l'objet QuestionDto recu
	 * et renvoit un nouveau QuestionDto avec l'id assigné à la question.
	 * 
	 * @Param JSON object { libelle,idCanal,idAuteur,propositions[{libelle, estCorrect}] }
	 */
	@RequestMapping( method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity saveQuestion(@RequestBody QuestionDto questionDto) {
		try {
			QuestionDto response = questionService.creerQuestion(questionDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}
	}
}

