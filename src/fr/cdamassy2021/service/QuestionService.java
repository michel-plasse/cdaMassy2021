package fr.cdamassy2021.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.controller.PropositionService;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Proposition;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.repository.PersonneRepository;
import fr.cdamassy2021.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	PersonneRepository personRepo;
	
	@Autowired
	PropositionService propService;
	
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
	
	/**
     * traite les informations recus dans la servlet pour construire et inserer
     * les modeles Question et Propositions dans la BDD.
     *
     * @param libelleQuestion
     * @param allPropositionsLibelles
     * @param allPropositionsCorrectnesses
     * @return
     * @throws SQLException
     */
    public Question creerQuestion(
            String libelleQuestion,
            String[] allPropositionsLibelles,
            ArrayList<String> allPropositionsCorrectnesses,
            Personne auteur,
            long canal) {
    	Question nouvelleQuestion = new Question();
    	nouvelleQuestion.setIdCanal(canal);
    	nouvelleQuestion.setAuteur(auteur);
    	nouvelleQuestion.setLibelle(libelleQuestion);
    	
    	Set<Proposition> props = new HashSet<Proposition>();
    	for(int i = 0; i<allPropositionsLibelles.length; i++)
    	{
    		Proposition prop = new Proposition();
    		prop.setEstCorrecte(Long.parseLong(allPropositionsCorrectnesses.get(i)));
    		prop.setLibelle(allPropositionsLibelles[i]);
    		props.add(prop);
    	}
    	nouvelleQuestion.setPropositions(props);
    	nouvelleQuestion.setIdTypeQuestion(3);
    	questionRepo.save(nouvelleQuestion);
    	for(Proposition prop:nouvelleQuestion.getPropositions()) {
    		prop.setQuestion(nouvelleQuestion);
    		propService.save(prop);
    	}
    	return nouvelleQuestion;
    }
    
    @Transactional
    public Question creerQuestion(
            String libelleQuestion,
            Personne auteur,
            long canal) {
    	Question nouvelleQuestion = new Question();
    	nouvelleQuestion.setIdCanal(canal);
    	nouvelleQuestion.setAuteur(auteur);
    	nouvelleQuestion.setLibelle(libelleQuestion);
    	nouvelleQuestion.setIdTypeQuestion(2);
    	return	questionRepo.save(nouvelleQuestion);
    }
}
