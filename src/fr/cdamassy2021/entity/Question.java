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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


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
	@OneToOne
    @JoinColumn(name = "id_createur")
	private Personne idCreateur;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idQuestion", cascade = CascadeType.ALL)
	private Collection<Proposition> propositions = new LinkedHashSet<Proposition>();
	
	@Column
	private long idTypeQuestion;
	//@Column
	//private long idQuestionnaire;
	
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Question(long idQuestion, String libelle, long idCanal,Personne idCreateur, long idTypeQuestion
			/*,long idQuestionnaire*/) {
		super();
		this.idQuestion = idQuestion;
		this.libelle = libelle;
		this.idCanal = idCanal;
		this.idTypeQuestion = idTypeQuestion;
		//this.idQuestionnaire = idQuestionnaire;
		this.idCreateur = idCreateur;
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

	public long getIdTypeQuestion() {
		return idTypeQuestion;
	}



	public void setIdTypeQuestion(long idTypeQuestion) {
		this.idTypeQuestion = idTypeQuestion;
	}

/*
	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}



	public void setIdQuestionnaire(long idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}

*/

	public Personne getIdCreateur() {
		return idCreateur;
	}



	public void setIdCreateur(Personne idCreateur) {
		this.idCreateur = idCreateur;
	}



	public Collection<Proposition> getPropositions() {
		return propositions;
	}



	public void setPropositions(Collection<Proposition> propositions) {
		this.propositions = propositions;
	}


	
}
