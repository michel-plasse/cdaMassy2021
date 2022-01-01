/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Reponse;
import fr.cdamassy2021.model.Sondage;
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
public class QuestionDaoImplTest extends Cdamassy2021Test {

    @Test
    public void testInsert() throws Exception {
        System.out.println("try insert");
        //given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        String enonceQuestion = "TEST insertion";
        int idCanalTest = 1;
        int idAuteurTest = 2;
        Question inserted = new Question(Question.TypeQuestion.QCM,
                idAuteurTest, idCanalTest, enonceQuestion);
        //when:
        dao.insert(inserted);
        //then:
        assertEquals(8, inserted.getId());
        assertEquals(enonceQuestion, inserted.getLibelle());
    }

    @Test
    public void testInsertQuestionQCM() throws Exception {
        System.out.println("try testInsert_avecDeuxPropositions");
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        //given:
        // Une Question 
        String enonceQuestion = "Je suis un TEST d'insertion";
        int idCanalTest = 1;
        int idAuteurTest = 2;
        Question insertedQuestion = new Question(
                Question.TypeQuestion.QCM,
                idAuteurTest, idCanalTest,
                enonceQuestion);

        // Une List<Proposition>
        List<Proposition> testedPropositions = new ArrayList<Proposition>();
        Proposition prop1 = new Proposition(Proposition.Correctness.CORRECT,
                "Je suis un TEST insertion proposition est_correct 1");
        Proposition prop2 = new Proposition(Proposition.Correctness.INCORRECT,
                "Je suis un TEST insertion proposition est_correct 0");
        Proposition prop3 = new Proposition(Proposition.Correctness.UNDEFINED,
                "Je suis un TEST insertion proposition est_correct 2");
        testedPropositions.add(prop1);
        testedPropositions.add(prop2);
        testedPropositions.add(prop3);
        //when:
        boolean result = dao.insert(insertedQuestion, testedPropositions);
        //then:
        for (Proposition p : testedPropositions) {
            System.out.println(p.toString());
        }
        assertEquals(true, result);
        assertEquals(8, insertedQuestion.getId());
        assertEquals(18, prop1.getIdProposition());
        assertEquals(19, prop2.getIdProposition());
        assertEquals(20, prop3.getIdProposition());
        assertEquals(Proposition.Correctness.CORRECT,
                dao.findPropositionById(18).getCorrectness());
        assertEquals(Proposition.Correctness.INCORRECT,
                dao.findPropositionById(19).getCorrectness());
        assertEquals(Proposition.Correctness.UNDEFINED,
                dao.findPropositionById(20).getCorrectness());

    }

    @Test
    public void testFindById() throws Exception {
        //given:
        System.out.println("try findById");
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        long testedId = 2;
        //when
        Question result = null;
        try {
            result = dao.findById(testedId);
        } catch (SQLException e) {
            System.out.println("Fail finding question where id=" + testedId);
        }
        //then
        int expectedId = 2;
        int expectedCanal = 1;
        int expectedAuteur = 1;
        int expectedNbReponses = 5;
        Question.TypeQuestion expectedType
                = Question.TypeQuestion.values()[3];

        Question expResult = new Question(
                expectedId, expectedType,
                expectedAuteur, expectedCanal, "Tryphon Tournesol",
                "Combien de temps voulez-vous pour ce TP ?");
        assertEquals(expResult, result);
        assertEquals(3, result.getPropositions().size());
        assertEquals(expectedNbReponses, result.getReponses().size());
    }

    @Test
    public void testFindByIdPasTrouve() throws SQLException {
        //given:
        System.out.println("try testFindByIdPasTrouve");
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        long testedId = -1;
        //when
        Question result = dao.findById(testedId);

        //then:
        assertEquals(null, result);
    }

    @Test
    public void testgetAllWithinLimit() throws SQLException {
        System.out.println("try getAllWithinLimit");
        //given: c'est des préréquis
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        List<Question> questions;
        //when : la methode que je test 
        questions = dao.getAllPaging(1, 10);
        //then: 
        int actualNbPropositions = 0;
        System.out.println("nbQuestion" + questions.size());
        for (Question q : questions) {
            for (Proposition p : q.getPropositions()) {
                actualNbPropositions++;
            }
        }
        int actualNbReponses = 0;
        for (Question q : questions) {
            actualNbReponses += q.getReponses().size();
        }
        assertEquals(7, questions.size());
        assertEquals(17, actualNbPropositions);
        assertEquals(14, actualNbReponses);
    }

    @Test
    public void testgetAllByCanalPaging() throws SQLException {
        System.out.println("try getAllByCanalPaging");
        // given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        List<Question> questions;
        int idCanal = 1;
        int noPage = 1;
        int nbElementsParPage = 10;
        // when:
        questions = dao.getAllByCanalPaging(idCanal, noPage, nbElementsParPage);
        // then:
        int actual = questions.size();
        assertEquals(7, actual);
    }

    @Test
    public void testgetAllSondages() throws SQLException {
        System.out.println("test getAllSondages()");
        //given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        //when:
        ArrayList<Sondage> sondages = dao.getAllSondagesPaging(1, 10);
        //then:
        assertEquals(7, sondages.size());
        assertEquals(2, sondages.get(0).getResults().size());
        assertEquals(3, sondages.get(1).getResults().size());
        System.out.println(sondages.get(0).getResults().get(0).NomsPersonnes);
    }

    @Test
    public void testgetAllPendingQuestions() throws SQLException {
        System.out.println("test getAllSondages()");
        //given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        //when:
        ArrayList<Question> pending1 = dao.getAllPendingQuestions(1, 1);
        ArrayList<Question> pending2 = dao.getAllPendingQuestions(1, 2);
        assertEquals(7, pending1.size());
        assertEquals(0, pending2.size());
    }

    @Test
    public void testinsertReponse() throws SQLException {
        System.out.println("test insertReponse()");
        //given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        Reponse newReponse = new Reponse();
        newReponse.setIdPersonne(2);
        newReponse.setIdQuestion(6);
        newReponse.setLibelle("TEST insertion reponse");
        //when:
        dao.insertReponse(newReponse);
        //then:
        assertEquals(15, dao.getAllReponses().size());
    }
    
        @Test
    public void testgetAllReponse() throws SQLException {
        System.out.println("test getAllReponse()");
        //given:
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        //when:
        ArrayList<Reponse> result = dao.getAllReponses();
        //then
        assertEquals(14,result.size());
    }
}
