/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.IQuestion;
import fr.cdamassy2021.model.QuestionFactory;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 *
 * @author thoma
 */
public class QuestionDaoTest {

    public QuestionDaoTest() {
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println("try insert");
        //given:
        QuestionDao instance = new QuestionDao();
        String enonceQuestion
                = "dites-moi si vous me trouvez dans la"
                + "database après le test d'insertion";
        Long idCanalTest = Long.valueOf(1);
        Long idAuteurTest = Long.valueOf(2);
        QuestionFactory qFactory = new QuestionFactory();
        IQuestion inserted = qFactory.createQuestion(IQuestion.TypeQuestion.QCM, idAuteurTest, idCanalTest, enonceQuestion, null);
        boolean expResult = true;
        //when:
        boolean result = instance.insert(inserted);
        //then:
        assertEquals(expResult, result);
    }

//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        IQuestion deleted = null;
//        QuestionDao instance = new QuestionDao();
//        instance.delete(deleted);
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testFindById() {
        //given:
        System.out.println("try findById");
        QuestionDao instance = new QuestionDao();
        long testedId = 2;
        //when
        IQuestion result = null;
        try {
            result = instance.findById(testedId);
        } catch (Exception e) {
            System.out.println("Fail finding question where id=" + testedId);
        }
        //then
        Long expectedId = Long.valueOf(2);
        Long expectedCanal = Long.valueOf(2);
        Long expectedAuteur = Long.valueOf(2);
        IQuestion.TypeQuestion expectedType
                = IQuestion.TypeQuestion.values()[3];

        IQuestion expResult = null;
        QuestionFactory qFactory = new QuestionFactory();
        try {
            expResult = qFactory.createQuestion(
                expectedId, expectedType,
                expectedAuteur, expectedCanal,
                "On a pas ramené un peu trop de croissant quand même là?",
                null);
        } catch (Exception e) {
            System.out.println("Factory can't create this QuestionBean");
        }

        assertEquals(expResult, result);
    }

//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        QuestionDao instance = new QuestionDao();
//        ArrayList<Question> expResult = null;
//        ArrayList<Question> result = instance.findAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
}
