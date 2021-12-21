/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe teste les select et insertion de la classe QuestionDao.
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
        int idCanalTest = 1;
        int idAuteurTest = 2;
        Question inserted = new Question(Question.TypeQuestion.QCM, idAuteurTest, idCanalTest, enonceQuestion, null);;
        boolean expResult = true;
        //when:
        boolean result = instance.insert(inserted);
        //then:
        assertEquals(8, inserted.getId());
        assertEquals(expResult, result);
    }

    @Test
    public void testInsertQuestionQCM() throws Exception {
        System.out.println("try testInsert_avecDeuxPropositions");
        QuestionDao instance = new QuestionDao();

        //given:
        // Une Question 
        String enonceQuestion = "Je suis un TEST d'insertion";
        int idCanalTest = 1;
        int idAuteurTest = 2;
        Question insertedQuestion = new Question(
                Question.TypeQuestion.QCM, 
                idAuteurTest, idCanalTest,
                enonceQuestion,
                null);

        // Une List<Proposition>
        List<Proposition> testedPropositions = new ArrayList<Proposition>();
        Proposition prop1 = new Proposition(Proposition.Correctness.CORRECT, "Je suis un TEST insertion propos est_correct 1");
        Proposition prop2 = new Proposition(Proposition.Correctness.INCORRECT, "Je suis un TEST insertion propos est_correct 0");
        Proposition prop3 = new Proposition(Proposition.Correctness.UNDEFINED, "Je suis un TEST insertion propos est_correct 2");
        testedPropositions.add(prop1);
        testedPropositions.add(prop2);
        testedPropositions.add(prop3);
        //when:
        boolean result = instance.insert(insertedQuestion, testedPropositions);
        //then:
        for (Proposition p : testedPropositions) {
            System.out.println(p.toString());
        }
        assertEquals(true, result);
        assertEquals(8, insertedQuestion.getId());
        assertEquals(18, prop1.getIdProposition());
        assertEquals(19, prop2.getIdProposition());
        assertEquals(20, prop3.getIdProposition());
    }

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
        int expectedCanal = 1;
        int expectedAuteur = 1;
        Question.TypeQuestion expectedType
                = Question.TypeQuestion.values()[3];

        Question expResult = new Question(
                expectedId, expectedType,
                expectedAuteur, expectedCanal, "Tryphon Tournesol",
                "Combien de temps voulez-vous pour ce TP ?",
                null);
        assertEquals(expResult, result);
        assertEquals(3, result.getPropositions().size());
    }

    @Test
    public void testFindByIdPasTrouve() throws SQLException {
        //given:
        System.out.println("try testFindByIdPasTrouve");
        QuestionDao instance = new QuestionDao();
        long testedId = -1;
        //when
        Question result = instance.findById(testedId);

        //then:
        assertEquals(null, result);
    }

    @Test
    public void testgetAllWithinLimit() throws SQLException {
        //given:
        System.out.println("try getAllWithinLimit");
        Dao dao = new QuestionDao();
        List<Question> questions = null;
        //when
        questions = dao.getAllPaging(1, 10);
        //then:
        int expected = 7;
        assertEquals(expected, questions.size());
        int expectedNbPropositions = 17;
        int actualNbProposition = 0;
        System.out.println("nbQuestion" + questions.size());
        for (Question q : questions) {
            for (Proposition p : q.getPropositions()) {
                actualNbProposition++;
            }
        }
        System.out.println("props count=" + actualNbProposition);
        assertEquals(expectedNbPropositions, actualNbProposition);
    }
}
