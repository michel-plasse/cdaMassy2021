/**
 * Groupe2
 * 23/12/2021
 */
package fr.cdamassy2021.dao;
/**
 * 
 * @author Groupe2
 * 23/12/2021
 */
import java.util.ArrayList;

import fr.cdamassy2021.model.Questionnaire;

public interface DaoFormateur extends DaoPersonne {
	
	ArrayList<Questionnaire> getQuestionnairesByIdFormateur(int idFormateur);

}
