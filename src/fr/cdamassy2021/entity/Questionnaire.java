package fr.cdamassy2021.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 *  @author Kamal, Ben, Vinoth, Thomas
 *  
 *  
 *  */

@Entity
public class Questionnaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idQuestionnaire;
	@Column
	private String libelle;
	@Column(name = "id_canal")
	private long idCanal;
	@OneToOne
	@JoinColumn(name = "id_createur")
	private Personne idCreateur;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private Collection<Question> allQuestions = new LinkedHashSet<Question>();
	
	@Column
	private String dateAjout;

	public Questionnaire() {
		super();
	}

	public Questionnaire(long idQuestionnaire, String libelle, long idCanal, Personne idCreateur,
			Collection<Question> allQuestions, String dateAjout) {
		super();
		this.idQuestionnaire = idQuestionnaire;
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.idCreateur = idCreateur;
		this.allQuestions = allQuestions;
		this.dateAjout = dateAjout;
	}

	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}

	public void setIdQuestionnaire(long idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
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

	public Personne getIdCreateur() {
		return idCreateur;
	}

	public void setIdCreateur(Personne idCreateur) {
		this.idCreateur = idCreateur;
	}

	public Collection<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(Collection<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}



}
