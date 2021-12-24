package fr.cdamassy2021.dao;


import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.GroupeEfg;

/**
 * Groupe2 23/12/2021
 */
class GroupeEfgDaoTest {

	DaoGroupeEfg daoGroupeEfg = DaoFactory.getInstance().getGroupeEfgDao();

	/**
	 * Groupe2 23/12/2021
	 */
	@Test
	void testFindByIdLongLong() {
		int idEfg = 1;
		int idCreateur = 3;
		GroupeEfg groupe = daoGroupeEfg.findById(idEfg, idCreateur);
		assert (groupe.getIdEfg() == 1 && groupe.getIdCreateur() == 3);
		idEfg = 5;
		idCreateur = 3;
		assert (null == daoGroupeEfg.findById(idEfg, idCreateur));
	}

	/**
	 * Groupe2 23/12/2021
	 * la methode insert est utilisée par la méthode creerGroupe de la classe EFG.
	 */
	@Test
	void testInsert() {
		int idEfg = 1;
		int idCreateurGroupe = 5;
		EFG efg = new EFG();
		efg.setIdEFG(idEfg);
		efg.creerGroupe(idCreateurGroupe);
		GroupeEfg groupe = daoGroupeEfg.findById(idEfg, idCreateurGroupe);
		assert (groupe.getIdEfg() == 1 && groupe.getIdCreateur() == 5);
		try {
			Database.reset(LocalDateTime.now());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Groupe2 23/12/2021
	 */
	@Test
	void testGetAllMembres() {
		assert (daoGroupeEfg.getAllMembresByIdGroupe(1, 3).size()==3);
		assert (null==daoGroupeEfg.getAllMembresByIdGroupe(20, 6));
	}
}
