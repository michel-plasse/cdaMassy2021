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

	@Test
	public void TestgetAllByByIdPersonne() throws Exception {

		List<Canal> listeCanaux = CanalDao.getAllByIdPersonne(1);

		int expected = 2;
		assertEquals(expected, listeCanaux.size());
		for (Canal listeC : listeCanaux) {
			System.out.println(listeC.getIdCanal() + "- " + listeC.getNomCanal());
		}
	}

	@Test
	public void TestgetMembresDuCanal() throws Exception {
		//
		// given:
		int idCanalTest = 1;
		List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanalTest);

		int expected = 7;
		assertEquals(expected, membresCanal.size());
		for (Personne mbreC : membresCanal) {
			System.out.println(mbreC.getId() + "- " + mbreC.getNom() + " " + mbreC.getPrenom());
		}
	}

}
