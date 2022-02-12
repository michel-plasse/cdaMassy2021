package fr.cdamassy2021.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	public void save(Question question) {
		questionRepo.save(question);
	}
	
	public List<Question> listAll() {
		return (List<Question>) questionRepo.findAll();
	}
	public Question findById(long questionId) {
		return questionRepo.findById(questionId).get();
	}
	
	public List<Question> listByCanal(long idCanal) {
		List<Question> allQuestions = questionRepo.findByCanal(idCanal);
		System.out.println("QuestionService");
		System.out.println(allQuestions);
		return allQuestions;
	}
	
	public List<Question> listPending(long idUser, long idCanal) {
		List<Question> allQuestions = questionRepo.findPending(idUser,idCanal);
		System.out.println("QuestionService : list pending questions");
		System.out.println(allQuestions);
		return allQuestions;
	}
	
}
