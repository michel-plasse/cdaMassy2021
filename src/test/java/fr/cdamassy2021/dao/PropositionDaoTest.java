package fr.cdamassy2021.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;

class PropositionDaoTest extends Cdamassy2021Test{

	@Test
	void testInsert() throws SQLException {
		System.out.println("try insert");
		//given:
		PropositionDao instance  = new PropositionDao();

		String enonceProposition
		= "dites-moi si vous me trouvez dans la"
				+ "database après le test d'insertion";
		int idTest = -1;
		int idQuestion = 1;
		Proposition inserted= new Proposition(idQuestion,
                        Proposition.Correctness.UNDEFINED, enonceProposition);
		boolean expResult = true;
		//when:
		boolean result = instance.insert(inserted);
		//then:
		assertEquals(16, inserted.getIdProposition());
		assertEquals(expResult, result);



	}

}
