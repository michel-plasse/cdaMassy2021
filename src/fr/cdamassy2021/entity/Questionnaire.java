package entity;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Groupe2 23/12/2021 - modifié cm 10/02/2022
 */

@Entity @Table(name = "questionnaire")
public class Questionnaire {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long idQuestionnaire;
	@Column
	Long idCreateur;
	@Column
	Long idCanal;
	@Column
	String libelle;
	@Column
	Date dateAjout;
	
	/**@OneToMany
	@JoinColumn(name = "question_fk")
	ArrayList<Question> questions = new ArrayList<Question>();**/

	public Questionnaire(Long idQuestionnaire, Long idCreateur, Long idCanal, String libelle, Date dateAjout) {
		super();
		this.idQuestionnaire = idQuestionnaire;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.libelle = libelle;
		this.dateAjout = dateAjout;
	}

	public Questionnaire(Long idCreateur, Long idCanal, String libelle) {
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
	public Long getIdQuestionnaire() {
		return idQuestionnaire;
	}

	/**
	 * @param idQuestionnaire the idQuestionnaire to set
	 */
	public void setIdQuestionnaire(Long idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}

	/**
	 * @return the idCreateur
	 */
	public Long getIdCreateur() {
		return idCreateur;
	}

	/**
	 * @param idCreateur the idCreateur to set
	 */
	public void setIdCreateur(Long idCreateur) {
		this.idCreateur = idCreateur;
	}

	/**
	 * @return the idCanal
	 */
	public Long getIdCanal() {
		return idCanal;
	}

	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(Long idCanal) {
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
	/**public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	/**public void setQuestions() {
		
		/**
		DaoQuestionnaire daoQuestionnaire = DaoFactory.getInstance().getQuestionnaireDao();
		this.questions = daoQuestionnaire.getAllQuestionsByIdQuestionnaire(idQuestionnaire);
		
	}**/

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return "Questionnaire [idQuestionnaire=" + idQuestionnaire + ", idCreateur=" + idCreateur + ", idCanal="
				+ idCanal + ", libelle=" + libelle + ", dateAjout=" + dateAjout + "]";
	}

}
