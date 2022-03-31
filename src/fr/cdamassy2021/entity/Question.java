package fr.cdamassy2021.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.cdamassy2021.enums.TypeQuestion;

/*
 * 
 *  @author Kamal, Ben, Vinoth, Thomas
 *  
 */
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_question")
	private long idQuestion;

	@Column
	private String libelle;

	@Column
	private long idCanal;

	@ManyToOne
	@JoinColumn(name = "id_createur")
	private Personne auteur;

	@ManyToOne
	@JoinColumn (name = "id_questionnaire")
	private Questionnaire questionnaire;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
	private Set<Proposition> propositions = new LinkedHashSet<Proposition>();


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
	private Set<Reponse> reponses = new LinkedHashSet<Reponse>();
	
	@Column(name = "id_type_question")
	@Enumerated(EnumType.ORDINAL)
	private TypeQuestion typeQuestion;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Question(String libelle, long idCanal, Personne auteur,
			Set<Proposition> propositions, TypeQuestion typeQuestion) {
		super();
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.auteur = auteur;
		this.propositions = propositions;
		this.typeQuestion = typeQuestion;
	}
	
	public Question(String libelle, long idCanal, Personne auteur,
			TypeQuestion typeQuestion) {
		super();
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.auteur = auteur;
		this.typeQuestion = typeQuestion;
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
	public Personne getAuteur() {
		return auteur;
	}
	public void setAuteur(Personne auteur) {
		this.auteur = auteur;
	}
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	public Set<Proposition> getPropositions() {
		return propositions;
	}
	public void setPropositions(Set<Proposition> propositions) {
		this.propositions = propositions;
	}
	@JsonManagedReference
	public Set<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	public TypeQuestion getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(TypeQuestion typeQuestion) {
		this.typeQuestion = typeQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auteur, idCanal, idQuestion, libelle, propositions, questionnaire, reponses, typeQuestion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(auteur, other.auteur) && idCanal == other.idCanal && idQuestion == other.idQuestion
				&& Objects.equals(libelle, other.libelle) && Objects.equals(propositions, other.propositions)
				&& Objects.equals(questionnaire, other.questionnaire) && Objects.equals(reponses, other.reponses)
				&& typeQuestion == other.typeQuestion;
	}

	
}
