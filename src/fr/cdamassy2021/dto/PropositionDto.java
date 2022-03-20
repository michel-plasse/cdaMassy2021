package fr.cdamassy2021.dto;


import fr.cdamassy2021.entity.Proposition;

public class PropositionDto {
	
	private final long idProposition;
	
	private final long idQuestion;
	
	private final String libelle;
	
	private final int estCorrecte;
	
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
