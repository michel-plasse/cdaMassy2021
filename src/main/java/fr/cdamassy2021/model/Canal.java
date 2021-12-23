package fr.cdamassy2021.model;

import java.util.ArrayList;
/**
 * @author Groupe2 23/12/2021
 */

public class Canal {
	private int idCanal;
	private String nomCanal;
	private ArrayList<Personne> membres;
	/**
	 * @return the idCanal
	 */
	public int getIdCanal() {
		return idCanal;
	}
	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}
	/**
	 * @return the nomCanal
	 */
	public String getNomCanal() {
		return nomCanal;
	}
	/**
	 * @param nomCanal the nomCanal to set
	 */
	public void setNomCanal(String nomCanal) {
		this.nomCanal = nomCanal;
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

}
