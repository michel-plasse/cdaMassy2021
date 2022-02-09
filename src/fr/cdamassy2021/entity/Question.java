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
	private long idQuestion;
	@Column
	private String libelle;
	@Column
	private long idCanal;
	@Column
	private long idCreateur;
	private Str
	@Column
	private long idTypeQuestion;
	@Column
	private long idQuestionnaire;
	
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Question(long idQuestion, String libelle, long idCanal, long idCreateur, long idTypeQuestion,
			long idQuestionnaire) {
		super();
		this.idQuestion = idQuestion;
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.idCreateur = idCreateur;
		this.idTypeQuestion = idTypeQuestion;
		this.idQuestionnaire = idQuestionnaire;
	}



	public long getIdQuestion() {
		return idQuestion;
	}



	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public long getIdCanal() {
		return idCanal;
	}



	public void setIdCanal(long idCanal) {
		this.idCanal = idCanal;
	}



	public long getIdCreateur() {
		return idCreateur;
	}



	public void setIdCreateur(long idCreateur) {
		this.idCreateur = idCreateur;
	}



	public long getIdTypeQuestion() {
		return idTypeQuestion;
	}



	public void setIdTypeQuestion(long idTypeQuestion) {
		this.idTypeQuestion = idTypeQuestion;
	}



	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}



	public void setIdQuestionnaire(long idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}


	

	
}
