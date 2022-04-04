package fr.cdamassy2021.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String libelle;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Personne> personnes;
	
	public Role() {
		personnes = new HashSet<Personne>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Personne> getUsers() {
		return personnes;
	}

	public void setUsers(Set<Personne> personnes) {
		this.personnes = personnes;
	}
	
	public void addPersonne(Personne personne) {
		this.personnes.add(personne);
	}
	
}