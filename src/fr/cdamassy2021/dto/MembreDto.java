package fr.cdamassy2021.dto;

import fr.cdamassy2021.entity.Personne;

public class MembreDto {
	private long idMembre;
	private String nom;
	private String prenom;
	
	public MembreDto() {
	}

	public MembreDto(Personne personne) {
		this.idMembre = personne.getIdPersonne();
		this.nom = personne.getNom();
		this.prenom = personne.getPrenom();
	}

	public long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(long idMembre) {
		this.idMembre = idMembre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	

}
