package fr.cdamassy2021.pkclasses;

import java.io.Serializable;
import java.util.Objects;

public class ReponsePK2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private long question;
    private long personne;
    

	public ReponsePK2() {
		super();
	}


	public long getPersonne() {
		return personne;
	}


	public void setPersonne(long personne) {
		this.personne = personne;
	}


	public long getQuestion() {
		return question;
	}

	public void setQuestion(long question) {
		this.question = question;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(personne, question);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReponsePK2 other = (ReponsePK2) obj;
		return personne == other.personne && question == other.question;
	}

	



	
    
}
