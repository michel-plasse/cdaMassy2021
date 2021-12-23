package fr.cdamassy2021.dao;

import java.util.ArrayList;

import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Questionnaire;
import fr.cdamassy2021.model.Sondage;

public interface DaoCanal extends Dao<Canal> {

public	ArrayList<Questionnaire> getAllQuestionnairesByIdCanal(int idCanal);
public 	ArrayList<EFG> getAllEfgsByIdCanal(int idCanal);
public 	ArrayList<Sondage> getAllSondagesByIdCanal(int idCanal);
public 	ArrayList<Personne> getAllMembresByIdCanal(int idCanal);


}
