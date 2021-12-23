package fr.cdamassy2021.dao;

import org.junit.jupiter.api.Test;

/**
 * Groupe2 23/12/2021
 */
class EfgDaoTest {

	DaoEFG daoEfg = DaoFactory.getInstance().getEfgDao();

	/**
	 * Groupe2 23/12/2021
	 */
	@Test
	void testGetAllGroupesByIdEfg() {
		int idEfg = 1;
		assert (3 == daoEfg.getAllGroupesByIdEfg(idEfg).size());
	}

}
