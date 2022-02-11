package fr.cdamassy2021.entity;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * 
 *  @author Kamal, Ben, Vinoth, Thomas
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
	
	@OneToOne
    @JoinColumn(name = "id_createur")
	private Personne auteur;

	@ManyToOne
	@JoinColumn (name = "id_questionnaire")
	private Questionnaire questionnaire;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idQuestion", cascade = CascadeType.ALL)
	private Collection<Proposition> propositions = new LinkedHashSet<Proposition>();
	
	@Column
	private long idTypeQuestion;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(String libelle, long idCanal, Personne auteur, Collection<Proposition> propositions,
			long idTypeQuestion) {
		super();
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.auteur = auteur;
		this.propositions = propositions;
		this.idTypeQuestion = idTypeQuestion;
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
	public Collection<Proposition> getPropositions() {
		return propositions;
	}
	public void setPropositions(Collection<Proposition> propositions) {
		this.propositions = propositions;
	}
	public long getIdTypeQuestion() {
		return idTypeQuestion;
	}
	public void setIdTypeQuestion(long idTypeQuestion) {
		this.idTypeQuestion = idTypeQuestion;
	}
	

	
	
}
