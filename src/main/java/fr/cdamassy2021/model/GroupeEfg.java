package fr.cdamassy2021.model;

import java.util.ArrayList;

/**
 * 
 * @author Groupe2 23/12/2021
 */
public class GroupeEfg {

	int idCreateur;
	int idEfg;
	ArrayList<Personne> membres;

	public GroupeEfg(int idCreateur, int idEfg, ArrayList<Personne> membres) {
		super();
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
		this.membres = membres;
	}

	public GroupeEfg() {
		super();

	}

	/**
	 * @return the idCreateur
	 */
	public int getIdCreateur() {
		return idCreateur;
	}

	/**
	 * @param idCreateur the idCreateur to set
	 */
	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}

	/**
	 * @return the idEfg
	 */
	public int getIdEfg() {
		return idEfg;
	}

	/**
	 * @param idEfg the idEfg to set
	 */
	public void setIdEfg(int idEfg) {
		this.idEfg = idEfg;
	}

	/**
	 * @return the membres
	 */
	public ArrayList<Personne> getMembres() {
		return membres;
	}

	/**
	 * @param membres the membres to set
	 */
	public void setMembres(ArrayList<Personne> membres) {
		this.membres = membres;
	}

	/**
	 * ajoute un membre au groupeEfg
	 * 
	 * @param membre
	 */
	public void ajouterMembre(Personne membre) {

		membres.add(membre);
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return "GroupeEfg [idCreateur=" + idCreateur + ", idEfg=" + idEfg + ", membres=" + membres.toString() + "]";
	}

}
