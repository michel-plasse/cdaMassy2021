package fr.cdamassy2021.dao;

import java.util.ArrayList;

import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Questionnaire;

/**
 * @author Groupe2 23/12/2021
 */
public class QuestionnaireDao implements DaoQuestionnaire {

	protected DaoFactory factory;

	/**
	 * @author Groupe2 23/12/2021
	 */
	public QuestionnaireDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public Questionnaire findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Questionnaire> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Questionnaire t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Questionnaire t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Questionnaire t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * reste à ecrire pour pouvoir afficher les question d'un questionnaire
	 * 
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public ArrayList<Question> getAllQuestionsByIdQuestionnaire(int idQuestionnaire) {
		// TODO Auto-generated method stub
		return null;
	}

}
