/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.Objects;

/**
 * Java Bean / POJO 'Proposition' <br/>
 *  !TO DO: en faire une interface future +- proche<br/>
 *      --------<br/>
 * Def: <br/>
 *      Action de proposer, d'offrir, <br/>
 * de suggérer qqch. à qqn. ce qui est proposé.<br/>
 *      ---------<br/>
 * Application: proposition de réponse<br/>
 * 
 *      Lorsqu'une 'Question' est éditée par un 'Membre' <br/>
 * une ou plusieurs 'Proposition' sont aussi renseignées par ce dernier <br>
 * si nécessaire. Le membre doit aussi qualifier ou non cette proposition de 
 * correcte ou incorrecte. 
 * Des 'Participant' pourront répondre par la suite à une 'Question' en <br/>
 * choisissant parmis ces options de réponses.<br/>
 *      ---------<br/>
 * types de propositions possibles: <br/>
 * OUI/NON<br/>

 * @author thoma
 */
public class Proposition {
    
    public enum Correctness {
        CORRECT,INCORRECT,UNDEFINED;
    }
    
    private int idProposition;
    private int idQuestion;
    private Correctness isCorrect;
    private String libelle;
    
    public Proposition() {
    	
    }

    	public Proposition(Correctness isCorrect, String libelle) {
		super();
		this.idQuestion = idQuestion;
		this.isCorrect = isCorrect;
		this.libelle = libelle;
	}
        
	public Proposition(int idQuestion, Correctness isCorrect, String libelle) {
		super();
		this.idQuestion = idQuestion;
		this.isCorrect = isCorrect;
		this.libelle = libelle;
	}

	public Proposition(int idProposition, int idQuestion, Correctness isCorrect, String libelle) {
		super();
		this.idProposition = idProposition;
		this.idQuestion = idQuestion;
		this.isCorrect = isCorrect;
		this.libelle = libelle;
	}

	public int getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Correctness getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Correctness isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Proposition [idProposition=" + idProposition + ", idQuestion=" + idQuestion + ", isCorrect=" + isCorrect
				+ ", libelle=" + libelle + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProposition, idQuestion, isCorrect, libelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		return idProposition == other.idProposition && idQuestion == other.idQuestion && isCorrect == other.isCorrect
				&& Objects.equals(libelle, other.libelle);
	}
    
    
    
    
    
}
