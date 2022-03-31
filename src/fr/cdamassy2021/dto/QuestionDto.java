package fr.cdamassy2021.dto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.entity.Questionnaire;

public class QuestionDto {
	
	private long idQuestion;

	private String libelle;

	private long idCanal;

	private long idAuteur;

	private String nomAuteur;

	private long idQuestionnaire;

	private Set<PropositionDto> propositions = new LinkedHashSet<PropositionDto>();

	private List<ReponseDto> reponses = new ArrayList<ReponseDto>();

	private String typeQuestion;
	
	public QuestionDto() {
		
	}
	
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

	public long getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(long idAuteur) {
		this.idAuteur = idAuteur;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}

	public long getIdQuestionnaire() {
		return idQuestionnaire;
	}

	public void setIdQuestionnaire(long idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}

	public Set<PropositionDto> getPropositions() {
		return propositions;
	}

	public void setPropositions(Set<PropositionDto> propositions) {
		this.propositions = propositions;
	}

	public List<ReponseDto> getReponses() {
		return reponses;
	}

	public void setReponses(List<ReponseDto> reponses) {
		this.reponses = reponses;
	}

	public String getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(String typeQuestion) {
		this.typeQuestion = typeQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAuteur, idCanal, idQuestion, idQuestionnaire, libelle, nomAuteur, propositions, reponses,
				typeQuestion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDto other = (QuestionDto) obj;
		return idAuteur == other.idAuteur && idCanal == other.idCanal && idQuestion == other.idQuestion
				&& idQuestionnaire == other.idQuestionnaire && Objects.equals(libelle, other.libelle)
				&& Objects.equals(nomAuteur, other.nomAuteur) && Objects.equals(propositions, other.propositions)
				&& Objects.equals(reponses, other.reponses) && Objects.equals(typeQuestion, other.typeQuestion);
	}


	

}
