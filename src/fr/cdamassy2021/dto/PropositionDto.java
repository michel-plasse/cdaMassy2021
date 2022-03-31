package fr.cdamassy2021.dto;


import fr.cdamassy2021.entity.Proposition;

public class PropositionDto {
	
	private long idProposition;
	
	private long idQuestion;
	
	private String libelle;
	
	private int estCorrecte;
	
	public PropositionDto() {
		
	}
	
	public PropositionDto(Proposition proposition) {
		this.idProposition = proposition.getIdProposition();
		this.idQuestion = proposition.getQuestion().getIdQuestion();
		this.libelle = proposition.getLibelle();
		this.estCorrecte = (proposition.getEstCorrecte()==null)? 2:proposition.getEstCorrecte();
	}

	public long getIdProposition() {
		return idProposition;
	}

	public long getIdQuestion() {
		return idQuestion;
	}

	public String getLibelle() {
		return libelle;
	}

	public int getEstCorrecte() {
		return estCorrecte;
	}

}
