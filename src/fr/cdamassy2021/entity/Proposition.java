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

import fr.cdamassy2021.dto.PropositionDto;

@Entity
public class Proposition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProposition;
	
	@ManyToOne
	@JoinColumn(name="id_question")
	private Question question;
	
	@Column
	private String libelle;
	@Column
	private Integer estCorrecte;
	
	public Proposition() {
		super();
	}
	public Proposition(PropositionDto prop) {
		super();
		this.libelle = prop.getLibelle();
		this.estCorrecte = prop.getEstCorrecte();
	}
	public Proposition(String libelle, int estCorrecte) {
		super();
		this.libelle = libelle;
		this.estCorrecte = estCorrecte;
	}

	public long getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(long idProposition) {
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

	public Integer getEstCorrecte() {
		return estCorrecte;
	}

	public void setEstCorrecte(Integer estCorrecte) {
		this.estCorrecte = estCorrecte;
	}

	
}