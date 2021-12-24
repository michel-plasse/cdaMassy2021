/**
 * 
 */
package fr.cdamassy2021.dao;

import java.util.ArrayList;

import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Questionnaire;

/**
 * @author Melhem
 *
 */
public interface DaoQuestionnaire extends Dao<Questionnaire> {

	ArrayList<Question> getAllQuestionsByIdQuestionnaire(int idQuestionnaire);

}
