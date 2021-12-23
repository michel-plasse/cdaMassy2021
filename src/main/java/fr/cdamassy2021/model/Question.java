package fr.cdamassy2021.model;

/**
 * @author Groupe2 23/12/2021
 */
public class Question {

	private int idQuestion, idQuestionnaire, idCanal, idCreateur, idTypeQuestion;
	private String Libelle;

	public Question() {
		super();

	}

	public Question(int idQuestion, int idQuestionnaire, int idCanal, int idCreateur, int id_type_question,
			String libelle) {
		super();
		this.idQuestion = idQuestion;
		this.idQuestionnaire = idQuestionnaire;
		this.idCanal = idCanal;
		this.idCreateur = idCreateur;
		this.idTypeQuestion = id_type_question;
		Libelle = libelle;
	}

	public Question(int idQuestionnaire, int idCanal, int idCreateur, int id_type_question, String libelle) {
		super();
		this.idQuestionnaire = idQuestionnaire;
		this.idCanal = idCanal;
		this.idCreateur = idCreateur;
		this.idTypeQuestion = id_type_question;
		Libelle = libelle;
	}

	/**
	 * @return the idCanal
	 */
	public int getIdCanal() {
		return idCanal;
	}

	/**
	 * @return the idCreateur
	 */
	public int getIdCreateur() {
		return idCreateur;
	}

	/**
	 * @return the idQuestion
	 */
	public int getIdQuestion() {
		return idQuestion;
	}

	/**
	 * @return the idQuestionnaire
	 */
	public int getIdQuestionnaire() {
		return idQuestionnaire;
	}

	/**
	 * @return the id_type_question
	 */
	public int getIdTypeQuestion() {
		return idTypeQuestion;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return Libelle;
	}

	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}

	/**
	 * @param idCreateur the idCreateur to set
	 */
	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}

	/**
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	/**
	 * @param idQuestionnaire the idQuestionnaire to set
	 */
	public void setIdQuestionnaire(int idQuestionnaire) {
		this.idQuestionnaire = idQuestionnaire;
	}

	/**
	 * @param id_type_question the id_type_question to set
	 */
	public void setIdTypeQuestion(int id_type_question) {
		this.idTypeQuestion = id_type_question;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return "Question [idQuestion=" + idQuestion + ", idQuestionnaire=" + idQuestionnaire + ", idCanal=" + idCanal
				+ ", idCreateur=" + idCreateur + ", idTypeQuestion=" + idTypeQuestion + ", Libelle=" + Libelle + "]";
	}

}
