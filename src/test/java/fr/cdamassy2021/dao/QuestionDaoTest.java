/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 *
 * @author thoma
 */
public class QuestionDaoTest extends Cdamassy2021Test {

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
        int idTest = -1;
        int idCanalTest = 1;
        int idAuteurTest = 2;
        Question inserted = new Question(idTest, Question.TypeQuestion.QCM, idAuteurTest, idCanalTest, enonceQuestion, null);;
        boolean expResult = true;
        //when:
        boolean result = instance.insert(inserted);
        //then:
        assertEquals(8, inserted.getId());
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
    public void testFindByIdTrouve() throws Exception {
        //given:
        System.out.println("try findById");
        QuestionDao instance = new QuestionDao();
        long testedId = 2;
        //when
        Question result = null;
        try {
            result = instance.findById(testedId);
        } catch (Exception e) {
            System.out.println("Fail finding question where id=" + testedId);
        }
        //then
        int expectedId = 2;
        int expectedCanal = 2;
        int expectedAuteur = 2;
        Question.TypeQuestion expectedType
                = Question.TypeQuestion.values()[3];

        Question expResult = new Question(
                expectedId, expectedType,
                expectedAuteur, expectedCanal,
                "On a pas ramené un peu trop de croissant quand même là?",
                null);

        assertEquals(expResult, result);
    }

    @Test
    public void testFindByIdPasTrouve() throws Exception {
        //given:
        System.out.println("try testFindByIdPasTrouve");
        QuestionDao instance = new QuestionDao();
        long testedId = 111;
        //when
        Question result = null;
        try {
            result = instance.findById(testedId);
        } catch (Exception e) {
            System.out.println("Fail finding question where id=" + testedId);
        }
        //then
        int expectedId = 2;
        int expectedCanal = 2;
        int expectedAuteur = 2;
        Question.TypeQuestion expectedType
                = Question.TypeQuestion.values()[3];

        Question expResult = new Question(
                expectedId, expectedType,
                expectedAuteur, expectedCanal,
                "On a pas ramené un peu trop de croissant quand même là?",
                null);

        assertEquals(expResult, result);
    }

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

