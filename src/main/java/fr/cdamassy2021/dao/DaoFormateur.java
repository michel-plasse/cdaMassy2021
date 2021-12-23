
package fr.cdamassy2021.dao;

import java.util.ArrayList;

import fr.cdamassy2021.model.Questionnaire;

public interface DaoFormateur extends DaoPersonne {
	
	ArrayList<Questionnaire> getQuestionnairesByIdFormateur(int idFormateur);

}
