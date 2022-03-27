package fr.cdamassy2021.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author borel,beatrice,huawei
 */
@Entity
public class Canal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCanal;
	
	@Column
	private String nom;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "membre_canal"
		, joinColumns = { @JoinColumn(name = "id_personne") }
		, inverseJoinColumns = {@JoinColumn(name = "id_canal") })
	private Set<Personne> allMembres;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "canal")
	private List<Questionnaire> questionnaires;
	
	public Canal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor
	 * 
	 * @param idCanal
	 * @param nom
	 */
	public Canal(long idCanal, String nomCanal) {
		// TODO Auto-generated constructor stub
		this.idCanal = idCanal;
		this.nom = nomCanal;

	}


	public long getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(long idCanal) {
		this.idCanal = idCanal;
	}

    public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Personne> getAllMembres() {
		return allMembres;
	}
	
	public void setAllMembres(Set<Personne> allMembres) {
		this.allMembres = allMembres;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}
	
	
	
}
