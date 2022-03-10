package fr.cdamassy2021.entity;

import java.util.Objects;

/** 
 * 
 * @author Kamal, Ben, Vinoth, Thomas
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
	private Question question;
	
	@Column
	private String libelle;
	@Column
	private int estCorrecte;
	
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

	

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getEstCorrecte() {
		return estCorrecte;
	}

	public void setEstCorrecte(int estCorrecte) {
		this.estCorrecte = estCorrecte;
	}

	
}