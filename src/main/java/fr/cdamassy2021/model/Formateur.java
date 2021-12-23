package fr.cdamassy2021.model;

/**
 * @author Groupe2 23/12/2021
 */
public class Formateur extends Personne {

	public Formateur() {
		super();

	}

	public Formateur(int id, String prenom, String nom, String email, String tel, String pwd) {
		super(id, prenom, nom, email, tel, pwd);

	}

	public Formateur(int id, String prenom, String nom) {
		super(id, prenom, nom);

	}

	public Formateur(String prenom, String nom, String email, String tel, String pwd) {
		super(prenom, nom, email, tel, pwd);

	}

}
