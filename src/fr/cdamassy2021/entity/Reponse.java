package fr.cdamassy2021.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fr.cdamassy2021.pkclasses.ReponsePK2;

/**
 * Une réponse est la réponse d'un utlisateur a un sondage.
 * @author thoma
 */
@Entity
@Table(name = "reponse")
@IdClass(ReponsePK2.class)
public class Reponse {
	
	@Id
	@ManyToOne
	@JoinColumn (name = "id_question")
	private Question question;
	
	@Id
	@ManyToOne
	@JoinColumn (name = "id_personne")
	private Personne personne;

	@Column
	private String libelle;

	@Column
	private String dateRendu;

	public Reponse() {

	}

	public Reponse( Personne personne, Question question, String libelle) {
		super();
		this.question = question;
		this.personne = personne;
		this.libelle = libelle;
	}

	@JsonBackReference
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(String dateRendu) {
		this.dateRendu = dateRendu;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dateRendu, libelle, personne, question.getIdQuestion());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reponse other = (Reponse) obj;
		return Objects.equals(dateRendu, other.dateRendu) && Objects.equals(libelle, other.libelle)
				&& Objects.equals(personne, other.personne) && Objects.equals(question, other.question);
	}

	@Override
	public String toString() {
		return "Reponse [personne=" + personne + ", libelle=" + libelle + ", dateRendu=" + dateRendu + "]";
	}	

}
