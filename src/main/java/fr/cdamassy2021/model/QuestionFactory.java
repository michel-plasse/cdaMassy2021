/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;

/**
 * Cette class a pour
 *
 * @author thoma
 */
public class QuestionFactory {

    public QuestionFactory() {

    }

    public QuestionQcmImpl createQuestionQcmIpml() {
        return new QuestionQcmImpl();
    }

    public IQuestion createQuestion(IQuestion.TypeQuestion type) throws Exception {
        switch (type) {
            case QCM:
                return new QuestionQcmImpl();
            default:
                throw new Exception("Question Type not implemented yet");
        }
    }

    public IQuestion createQuestion(IQuestion.TypeQuestion type, Long idAuteur, Long idCanal, String enonceQuestion,
            ArrayList<Proposition> propositions) throws Exception {
        switch (type) {
            case QCM:
                return new QuestionQcmImpl(idCanal, idAuteur, type, enonceQuestion, propositions);
            default:
                throw new Exception("Question Type not implemented yet");
        }
    }

    public IQuestion createQuestion(Long id, IQuestion.TypeQuestion type, Long idAuteur, Long idCanal, String enonceQuestion,
            ArrayList<Proposition> propositions) throws Exception {
        switch (type) {
            case QCM:
                return new QuestionQcmImpl(idCanal, idAuteur, type, enonceQuestion, propositions);
            default:
                throw new Exception("Question Type not implemented yet");
        }
    }

}
