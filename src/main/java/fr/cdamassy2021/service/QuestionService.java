/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.service;

import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Traites les informations recues dans les servlet, construire les modeles
 * 'Question' et 'Proposition' et utiliser la dao pour toute opération
 * d'insertio ou d'updates.
 *
 * @author thoma
 */
public class QuestionService {

    public QuestionService() {

    }

    /**
     * traite les informations recus dans la servlet pour construire et inserer
     * les modeles Question et Propositions dans la BDD.
     *
     * @param libelleQuestion
     * @param allPropositionsLibelles
     * @param allPropositionsCorrectnesses
     * @return
     * @throws SQLException
     */
    public boolean creerQuestion(
            String libelleQuestion,
            String[] allPropositionsLibelles,
            ArrayList<String> allPropositionsCorrectnesses,
            int idAuteur)
            throws SQLException {
        boolean operationResult = false;

        ArrayList<Proposition.Correctness> allCorrectnesses = new ArrayList<>();
        for (String str : allPropositionsCorrectnesses) {
            switch (str) {
                case "Undefined": {
                    allCorrectnesses.add(Proposition.Correctness.UNDEFINED);
                    break;
                }
                case "Correct": {
                    allCorrectnesses.add(Proposition.Correctness.CORRECT);
                    break;
                }
                case "Incorrect": {
                    allCorrectnesses.add(Proposition.Correctness.INCORRECT);
                    break;
                }
                default:
                    operationResult = false;
            }
        }
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        //create Question bean:
        Question newQuestion = new Question(Question.TypeQuestion.QCM, 1, idAuteur, libelleQuestion, null);
        //creates List<Proposition>:
        ArrayList<Proposition> newPropositions = new ArrayList();
        if (allPropositionsLibelles != null) {
            for (int i = 0; i < allPropositionsLibelles.length; i++) {
                newPropositions.add(
                        new Proposition(-1,
                                allCorrectnesses.get(i),
                                allPropositionsLibelles[i]));
            }
        }
        try {
            operationResult = dao.insert(newQuestion, newPropositions);
        } catch (Exception e) {
            operationResult = false;
        }
        return operationResult;
    }
}
