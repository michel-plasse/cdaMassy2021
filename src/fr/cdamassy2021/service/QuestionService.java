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
import fr.cdamassy2021.enums.TypeQuestion;
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
	
	/**
	 * Retourne la Question pour laquelle id = questionId
     *
     * @param questionId : id de la question recherchée
     * @return Question pour laquelle id = questionId
     * @throws SQLException
     */
	public Question findById(long questionId) {
		return questionRepo.findById(questionId).get();
	}
	
	/**
	 * Retourne une liste de toutes les questions du canal pour lesquelles id=idCanal.
     *
     * @param idCanal : id du Canal dans duquel on extrait la liste de questions.
     * @return List<Question> pour lesquelles canal.id = idCanal
     * @throws SQLException
     */
	public List<Question> listByCanal(long idCanal) {
		List<Question> allQuestions = questionRepo.findByCanal(idCanal);
		System.out.println("QuestionService");
		System.out.println(allQuestions);
		return allQuestions;
	}
	
	/**
	 * Retourne une liste de toutes les questions postées sur le canal
	 * ayant id=idCanal et pour lequelles il n'y'a pas encore de réponse
	 * de l'utilisateur qui a pour id=idUser.
     *
     * @param idUser : id de l'Utilisateur pour lequel operer la recherche.
     * @param idCanal : id du Canal dans lequel operer la recherche.
     * @return List<Question> 
     * @throws SQLException
     */
	public List<Question> listPending(long idUser, long idCanal) {
		List<Question> allQuestions = questionRepo.findPending(idUser,idCanal);
		System.out.println("QuestionService : list pending questions");
		System.out.println(allQuestions);
		return allQuestions;
	}
	
	/**
     * Instancie une question ainsi que ses proposition chargées à partir des
     * données recues en parametres.
     * La question instanciée a pour type 'CHOIXMULTIPLES'
     * Fait persister la nouvelle question et la retourne avec son id.
     *
     * Require: L'index des libelles de propositions dans la liste doit correspondre a 
     * l index de leur valeur d'exactitude dans la liste de propositionsCorrectness
     *
     * @param libelleQuestion
     * @param allPropositionsLibelles : tableau des libellés des propositions
     * @param allPropositionsCorrectnesses : liste des valeurs d'exactitude des propositions
     * @return Question chargée de l'id attribué à l'insertion
     * @throws SQLException
     */
    public Question creerQuestionChoixMultiples(
            String libelleQuestion,
            String[] allPropositionsLibelles,
            ArrayList<String> allPropositionsCorrectnesses,
            Personne auteur,
            long canal) {
    	
    	Set<Proposition> props = new HashSet<Proposition>();
    	for(int i = 0; i<allPropositionsLibelles.length; i++)
    	{
    		Proposition prop = new Proposition();
    		prop.setEstCorrecte(Integer.parseInt(allPropositionsCorrectnesses.get(i)));
    		prop.setLibelle(allPropositionsLibelles[i]);
    		props.add(prop);
    	}
    	
    	Question nouvelleQuestion = new Question(
    			libelleQuestion,canal, auteur,
    			props,TypeQuestion.CHOIXMULTIPLES);
    	for(Proposition prop:nouvelleQuestion.getPropositions()) {
    		prop.setQuestion(nouvelleQuestion);
    	}
    	return questionRepo.save(nouvelleQuestion);
    }
    
	
	/**
     * Instancie une question à partir des données recues en paramètres.
     * La question instanciée aura pour type 'LIBRE'
     * Fait persister la nouvelle question et la retourne avec son id.
     *
     * Require: L'index des libelles de propositions dans la liste doit correspondre a 
     * l index de leur valeur d exactitude dans la liste de propositionsCorrectness
     *
     * @param libelleQuestion
     * @param allPropositionsLibelles : tableau des libelle de proposition
     * @param allPropositionsCorrectnesses : liste des valeurs d exactirudes des propositions
     * @return Question chargée de l'id attribué à l'insertion
     * @throws SQLException
     */
    @Transactional
    public Question creerQuestionLibre(
            String libelleQuestion,
            Personne auteur,
            long canal) {
    	Question nouvelleQuestion = new Question(
    			libelleQuestion, canal, auteur, TypeQuestion.LIBRE);
    	return	questionRepo.save(nouvelleQuestion);
    }
}
