/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdamassy2021.service.QuestionService;

/**
 *
 * @author thoma
 */
@WebServlet(name = "CreerQuestionServlet", urlPatterns = {"/creationQuestion"})
public class CreerQuestionServlet extends HttpServlet {

    private final String VUE = "WEB-INF/creationQuestion.jsp";

    /**
     * Redirige l'utilisateur vers un formulaire d'edition de nouvelle question
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);

    }

    /**
     * Recupere le formulaire Post recu d'editionQuestion.jps test les données
     * recues de l'utilisateur Si le formulaire est valide -
     * IQuestionDao.insert() Sinon - message d'erreur renvoyé
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue = VUE;
        boolean valideForm = true;

        //recuperation et test d'acceptation des données
        //recupere libelle question
        String libelleQuestion = request.getParameter("libelleQuestion");
        //test acceptation:
        boolean isLegitQuestion = (libelleQuestion.length() <= 255
                && libelleQuestion.length() > 0) ? true : false;

        //recupere les libelles des propositions dans un tableau
        String[] allPropositions = request.getParameterValues("proposition");
        //test acceptation
        boolean isLegitPropositions = true;
        if (allPropositions != null) {
            for (String proposition : allPropositions) {
                if (proposition.length() > 255 || proposition.length() == 0) {
                    isLegitPropositions = false;
                }
            }
        }

        //recupere la value de est_correct des propositions dans un tableau
        ArrayList<String> allCorrectnesses = new ArrayList<>();
        allCorrectnesses.add(request.getParameter("correctness"));
        for (int i = 2; i < allPropositions.length + 1; i++) {
            allCorrectnesses.add(request.getParameter("correctness" + i));
        }
        
        //test de validité du formulaire
        boolean valide = true;

        if (!isLegitQuestion) {
            request.setAttribute("message", "question null ou Trop de caracteres (max=255)");
            valide = false;
        }
        if (!isLegitPropositions) {
            request.setAttribute("message", "proposition null ou Trop de caracteres (max=255)");
            valide = false;
        }
        boolean operationOk = false;
        if (valide) {
            // callService:
            QuestionService qService = new QuestionService();
            try {
                operationOk = qService.insererNouvelleQuestion(
                        libelleQuestion,
                        allPropositions,
                        allCorrectnesses);
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    request.setAttribute("message", "Cette question existe dejà !");
                    valideForm = false;
                } else {
                    request.setAttribute("message", "Problème interne !");
                    valideForm = false;
                }
                Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // redirection
        if (valideForm && valide && operationOk) {
            request.setAttribute("messageSuccess", "Votre Question est validée !"
                    + " Vous pouvez vous maintenant la trouver dans la"
                    + "liste de vos questions.");
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
}