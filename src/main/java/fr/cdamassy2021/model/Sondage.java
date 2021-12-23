package fr.cdamassy2021.model;

public class Sondage extends Question {

	/**
	 * 
	 */
	public Sondage() {
		super();

	}

	/**
	 * @param idQuestion
	 * @param idQuestionnaire
	 * @param idCanal
	 * @param idCreateur
	 * @param id_type_question
	 * @param libelle
	 */
	public Sondage(int idQuestion, int idQuestionnaire, int idCanal, int idCreateur, int id_type_question,
			String libelle) {
		super(idQuestion, idQuestionnaire, idCanal, idCreateur, id_type_question, libelle);

	}

	/**
	 * @param idQuestionnaire
	 * @param idCanal
	 * @param idCreateur
	 * @param id_type_question
	 * @param libelle
	 */
	public Sondage(int idQuestionnaire, int idCanal, int idCreateur, int id_type_question, String libelle) {
		super(idQuestionnaire, idCanal, idCreateur, id_type_question, libelle);

	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
