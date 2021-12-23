package fr.cdamassy2021.model;

import java.sql.Date;
import java.util.ArrayList;

import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.DaoFormateur;
import fr.cdamassy2021.dao.DaoQuestionnaire;

/**
 * @author Groupe2 23/12/2021
 */
public class Questionnaire {

	int idQuestionnaire;
	int idCreateur;
	int idCanal;
	String libelle;
	Date dateAjout;
	ArrayList<Question> questions;

	public Questionnaire(int idQuestionnaire, int idCreateur, int idCanal, String libelle, Date dateAjout) {
		super();
		this.idQuestionnaire = idQuestionnaire;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.libelle = libelle;
		this.dateAjout = dateAjout;
	}

	public Questionnaire(int idCreateur, int idCanal, String libelle) {
		super();
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.libelle = libelle;
	}

	public Questionnaire() {
		super();

	}

	/**
	 * @return the idQuestionnaire
	 */
	public int getIdQuestionnaire() {
		return idQuestionnaire;
	}

	/**
	 * @param idQuestionnaire the idQuestionnaire to set
	 */
	public void setIdQuestionnaire(int idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
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
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the dateAjout
	 */
	public Date getDateAjout() {
		return dateAjout;
	}

	/**
	 * @param dateAjout the dateAjout to set
	 */
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions() {
		DaoQuestionnaire daoQuestionnaire = DaoFactory.getInstance().getQuestionnaireDao();
		this.questions = daoQuestionnaire.getAllQuestionsByIdQuestionnaire(idQuestionnaire);
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return "Questionnaire [idQuestionnaire=" + idQuestionnaire + ", idCreateur=" + idCreateur + ", idCanal="
				+ idCanal + ", libelle=" + libelle + ", dateAjout=" + dateAjout + "]";
	}

}
