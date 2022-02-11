package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Une réponse est la réponse d'un utlisateur a un sondage.
 * @author thoma
 */
@Entity
public class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReponse;
	
	@ManyToOne
    @JoinColumn(name = "id_personne")
    private Personne auteur;
    
	@ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;
	
	@Column
    private String libelle;
	
	@Column
    private String dateRendu;

    public Reponse() {

    }

	public Reponse(Personne auteur, Question question, String libelle, String dateRendu) {
		super();
		this.auteur = auteur;
		this.question = question;
		this.libelle = libelle;
		this.dateRendu = dateRendu;
	}

	public long getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(long idReponse) {
		this.idReponse = idReponse;
	}

	public Personne getAuteur() {
		return auteur;
	}

	public void setAuteur(Personne auteur) {
		this.auteur = auteur;
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

	public String getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(String dateRendu) {
		this.dateRendu = dateRendu;
	}

}
