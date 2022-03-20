package fr.cdamassy2021.dto;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Reponse;

public class ReponseDto {
	private final long idQuestion;
	
	private final long idAuteur;
	
	private final String nomAuteur;

	private final String libelle;

	private final String dateRendu;
	
	public ReponseDto(Reponse reponse) {
		Personne auteur  = reponse.getPersonne();
		this.idAuteur = auteur.getIdPersonne();
		this.nomAuteur = auteur.getPrenom() +" "+ auteur.getNom();
		this.idQuestion = reponse.getQuestion().getIdQuestion();
		this.libelle = reponse.getLibelle();
		this.dateRendu = reponse.getDateRendu();
	}

	public long getIdQuestion() {
		return idQuestion;
	}

	public long getIdAuteur() {
		return idAuteur;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getDateRendu() {
		return dateRendu;
	}
	
}
