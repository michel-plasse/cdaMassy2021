package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 *  @author chang
 *  
 *  
 *  */
@Entity

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idQuestion;
	@Column
	private String libelle;
	@Column
	private String idCanal;
	@Column
	private String idCreateur;
	@Column
	private String idTypeQuestion;
	@Column
	private String idQuestionnaire;
	
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Question(String idQuestion, String libelle, String idCanal, String idCreateur, String idTypeQuestion,
			String idQuestionnaire) {
		super();
		this.idQuestion = idQuestion;
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.idCreateur = idCreateur;
		this.idTypeQuestion = idTypeQuestion;
		this.idQuestionnaire = idQuestionnaire;
	}



	public String getIdQuestion() {
		return idQuestion;
	}



	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public String getIdCanal() {
		return idCanal;
	}



	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}



	public String getIdCreateur() {
		return idCreateur;
	}



	public void setIdCreateur(String idCreateur) {
		this.idCreateur = idCreateur;
	}



	public String getIdTypeQuestion() {
		return idTypeQuestion;
	}



	public void setIdTypeQuestion(String idTypeQuestion) {
		this.idTypeQuestion = idTypeQuestion;
	}



	public String getIdQuestionnaire() {
		return idQuestionnaire;
	}



	public void setIdQuestionnaire(String idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}


	

	
}
