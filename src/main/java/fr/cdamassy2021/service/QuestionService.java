/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.service;

import fr.cdamassy2021.dao.QuestionDaoImpl;
import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Ce service se charge de traiter les informations recus dans les servlet,
 * construire les modeles 'Question' et 'Proposition'
 * et utiliser la dao pour toute opération d'insertio ou d'updates.
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
    public boolean insererNouvelleQuestion(
            String libelleQuestion,
            String[] allPropositionsLibelles,
            ArrayList<String> allPropositionsCorrectnesses)
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
        QuestionDaoImpl dao = new QuestionDaoImpl();
        //create Question bean:
        Question newQuestion = new Question(Question.TypeQuestion.QCM, 1, 1, libelleQuestion, null);
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
