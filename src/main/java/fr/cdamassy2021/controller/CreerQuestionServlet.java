/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
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
     * Recupere le formulaire Post recu d'editionQuestion.jps
     * test les données recues de l'utilisateur
     * Si le formulaire est valide
     * - QuestionDao.insert()
     * Sinon
     * - message d'erreur renvoyé
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
        boolean isLegitQuestion = (libelleQuestion.length() <= 255) ? true : false;

        //recupere les libelles des propositions dans un tableau
        String[] allPropositions = request.getParameterValues("proposition");
        //test acceptation
        boolean isLegitPropositions = true;
        if(allPropositions!=null)
        {
            for (String proposition : allPropositions) {
            if (libelleQuestion.length() > 255) {
                isLegitPropositions = false;
            }
        }

        }

        //test de validation du formulaire
        boolean valide = true;

        if (!isLegitQuestion) {
            request.setAttribute("erreur_Question", "Trop de caracteres (max=255)");
            valide = false;
        }
        if (!isLegitPropositions) {
            request.setAttribute("erreur_proposition", "Trop de caracteres (max=255)");
            valide = false;
        }


        if (valide) {
            QuestionDao dao = new QuestionDao();
            //create Question bean:
            Question newQuestion = new Question(Question.TypeQuestion.QCM,1,1,libelleQuestion,null);
            //creates List<Proposition>:
            ArrayList<Proposition> newPropositions = new ArrayList();
            for (String libelleProposition : allPropositions){
                // retire les propositions dont le libelle n'a pas été renseigné
                if (libelleProposition.length()>0){
                    newPropositions.add(
                        new Proposition(-1,
                                Proposition.Correctness.UNDEFINED,
                                libelleProposition));
                }
            }

            try {
                dao.insert(newQuestion, newPropositions);

            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    request.setAttribute("message", "Cette question existe dejà !");
                    valideForm = false;
                } else {
                    request.setAttribute( "message", "Problème interne !");
                    valideForm = false;
                }
                Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // redirection
        if (valideForm && valide) {
            request.setAttribute("messageSuccess", "Votre Question est validée !"
                    + " Vous pouvez vous maintenant la trouver dans la"
                    + "liste de vos questions.");
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
}
