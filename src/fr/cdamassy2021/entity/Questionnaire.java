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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_canal", nullable=false)
	private Canal canal;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_createur")
	private Personne createur;
	//private Personne createur ??? Christian 200220327
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private Collection<Question> allQuestions = new LinkedHashSet<Question>();
	
	
	@Column
	private String dateAjout;

	public Questionnaire() {
		super();
	}

	public Questionnaire(long idQuestionnaire, String libelle, Canal canal, Personne createur,
			Collection<Question> allQuestions, String dateAjout) {
		super();
		this.idQuestionnaire = idQuestionnaire;
		this.libelle = libelle;
		this.canal = canal;
		this.createur = createur;
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

	public Canal getIdCanal() {
		return canal;
	}

	public void setIdCanal(Canal canal) {
		this.canal = canal;
	}

	public Personne getCreateur() {
		return createur;
	}

	public void setCreateur(Personne createur) {
		this.createur = createur;
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

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}



}
