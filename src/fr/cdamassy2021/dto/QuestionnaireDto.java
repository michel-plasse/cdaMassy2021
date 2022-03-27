package fr.cdamassy2021.dto;

import java.util.Collection;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.entity.Questionnaire;

/**
 * 
 *  @author Goupe 2 (Christian, Melhem)
 *  
 *  
 *  */

public class QuestionnaireDto {
	
	private long idCanal;
	private long idCreateur;
	private long idQuestionnaire;
	private String libelle;
	private String dateAjout;
	private String nomCreateur;
	private String prenomCreateur;


	
	public QuestionnaireDto(Questionnaire questionnaire) {

		this.idCanal = questionnaire.getCanal().getIdCanal();
		this.idCreateur = questionnaire.getCreateur().getIdPersonne();
		this.dateAjout = questionnaire.getDateAjout().toString();
		this.idQuestionnaire = questionnaire.getIdQuestionnaire();
		this.libelle = questionnaire.getLibelle();
		this.nomCreateur = questionnaire.getCreateur().getNom();
		this.prenomCreateur = questionnaire.getCreateur().getPrenom();

	}
	
	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}

	public String getLibelle() {
		return libelle;
	}
	
	public long getIdCanal() {
		return idCanal;
	}

	public long getIdCreateur() {
		return idCreateur;
	}

	public String getNomCreateur() {
		return nomCreateur;
	}

	public String getPrenomCreateur() {
		return prenomCreateur;
	}

	public String getDateAjout() {
		return dateAjout;
	}
	

}

	