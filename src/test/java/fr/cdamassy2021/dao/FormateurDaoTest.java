package fr.cdamassy2021.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import fr.cdamassy2021.model.Formateur;
import fr.cdamassy2021.model.Personne;

class FormateurDaoTest {
	
	
	DaoFormateur daoFormateur = DaoFactory.getInstance().getFormateurDao();
	@Test
	void testFindById() {
		int idFormateur = 1;
		assert(daoFormateur.findById(idFormateur).getId()==1);
	}

	@Test
	void testFindAll() {
		assert(daoFormateur.findAll().size()==2);
	}
	
	@Test
	void testInsert() {
		Formateur formateur= new Formateur("Bobo","Toto","lll","hhh","kkk");
		int id=daoFormateur.insert(formateur);
		assertTrue(daoFormateur.findById(id).getEmail().equals("lll"));
		try {
			Database.reset(LocalDateTime.now() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testUpdate() {
		
	}

	@Test
	void testDelete() {
		
	}

	@Test
	void testGetQuestionnairesByIdFormateur() {
		int idFormateur = 1;
		assert(daoFormateur.getQuestionnairesByIdFormateur(idFormateur).size()==2);
	}

}
