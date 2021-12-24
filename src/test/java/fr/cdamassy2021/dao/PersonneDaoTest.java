package fr.cdamassy2021.dao;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonneDaoTest {
DaoPersonne daoPersonne=DaoFactory.getInstance().getPersonneDao();
	@Test
	void testFindById() {
		int idPersonne=1;
		assert(null!=daoPersonne.findById(idPersonne));
		idPersonne=15;
		assert(null==daoPersonne.findById(idPersonne));

	}

	@Test
	void testFindAll() {
		
	}

	@Test
	void testInsert() {
		
	}

}
