package fr.cdamassy2021.entity;

/** 
 * 
 * @author chang
 * 
 * 
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Proposition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idProposition;
	@ManyToOne
	@JoinColumn(name="id_question")
	private Question idQuestion;
	@Column
	private String libelle;
	@Column
	private String estCorrecte;
	
	public Proposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(String idProposition) {
		this.idProposition = idProposition;
	}

	public Question getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Question idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getEstCorrecte() {
		return estCorrecte;
	}

	public void setEstCorrecte(String estCorrecte) {
		this.estCorrecte = estCorrecte;
	}
	
}