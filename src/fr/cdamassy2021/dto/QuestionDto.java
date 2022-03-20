package fr.cdamassy2021.dto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.entity.Questionnaire;

public class QuestionDto {
	
	private final long idQuestion;

	private final String libelle;

	private final long idCanal;

	private final long idAuteur;

	private final String nomAuteur;

	private final long idQuestionnaire;

	private final Set<PropositionDto> propositions = new LinkedHashSet<PropositionDto>();

	private final List<ReponseDto> reponses = new ArrayList<ReponseDto>();

	private final String typeQuestion;
	
	public QuestionDto(Question question) {
		this.idQuestion = question.getIdQuestion();
		this.libelle = question.getLibelle();
		this.idCanal = question.getIdCanal();
		Personne auteur = question.getAuteur();
		this.idAuteur = auteur.getIdPersonne();
		this.nomAuteur = auteur.getPrenom() + " " + auteur.getNom();
		Questionnaire questionnaire = question.getQuestionnaire();
		this.idQuestionnaire = (questionnaire == null)? 0 : questionnaire.getIdQuestionnaire();
		this.typeQuestion = question.getTypeQuestion().name();
		question.getPropositions().forEach(q -> propositions.add(new PropositionDto(q)));
		question.getReponses().forEach(r -> reponses.add(new ReponseDto(r)));
	}

	public long getIdQuestion() {
		return idQuestion;
	}

	public String getLibelle() {
		return libelle;
	}

	public long getIdCanal() {
		return idCanal;
	}

	public long getIdAuteur() {
		return idAuteur;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}

	public Set<PropositionDto> getPropositions() {
		return propositions;
	}

	public List<ReponseDto> getReponses() {
		return reponses;
	}

	public String getTypeQuestion() {
		return typeQuestion;
	}
	
	

}
