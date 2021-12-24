package fr.cdamassy2021.dao;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Groupe2 23/12/2021
 */
class CanalDaoTest {

	DaoCanal daoCanal = DaoFactory.getInstance().getCanalDao();
	int idCanal = 1;

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Test
	void testGetAllQuestionnairesByIdCanal() {
		assert (2 == daoCanal.getAllQuestionnairesByIdCanal(idCanal).size());
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Test
	void testGetAllEfgsByIdCanal() {

		int nb = daoCanal.getAllEfgsByIdCanal(idCanal).size();
		assertEquals(2, nb);
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Test
	void testGetAllSondagesByIdCanal() {
		assert (7 == daoCanal.getAllSondagesByIdCanal(idCanal).size());
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Test
	void testGetAllMembresByIdCanal() {
		assert (7 == daoCanal.getAllMembresByIdCanal(idCanal).size());

	}

}
