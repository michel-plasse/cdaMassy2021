/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.OptionnalResponse;
import fr.cdamassy2021.model.Question;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
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
        String enonceQuestion = "This is a YES/NO test question";
        Long idCanalTest= Long.valueOf(1);
        Long idAuteurTest= Long.valueOf(2);
        Question inserted = new Question(idCanalTest, idAuteurTest, OptionnalResponse.ReponseType.YESNO, enonceQuestion, null);
        System.out.println(OptionnalResponse.ReponseType.YESNO.ordinal());
        boolean expResult = true;
        //when:
        boolean result = instance.insert(inserted);
        //then:
        assertEquals(expResult, result);
    }

//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Question deleted = null;
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
        Question result = null;
        try {
            result = instance.findById(testedId);
        } catch (Exception e) {
            System.out.println("Fail finding question where id="+testedId);
        }
        //then
        Long expectedId = Long.valueOf(2);
        Long expectedCanal= Long.valueOf(2);
        Long expectedAuteur= Long.valueOf(2);
        OptionnalResponse.ReponseType expectedType 
                = OptionnalResponse.ReponseType.values()[1];
        Question expResult = new Question(expectedId,expectedCanal,expectedAuteur,expectedType,"Ceci est la deuxieme question",null);

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
