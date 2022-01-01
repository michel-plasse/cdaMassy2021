/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Reponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thoma
 */
@WebServlet(name = "EnvoyerReponseServlet", urlPatterns = {"/envoyerreponseservlet"})
public class EnvoyerReponseServlet extends HttpServlet {
    private final String VUE_OK = "WEB-INF/activitequestions.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";
    
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
        String vue = VUE_OK;
        boolean valideForm = true;
        String libelleReponse = request.getParameter("reponse");
        int questionId = Integer.parseInt(request.getParameter("question"));
        //test acceptation:
        boolean isLegitReponse = (libelleReponse.length() <= 255
                && libelleReponse.length() > 0) ? true : false;

        
        boolean valide = true;

        if (!isLegitReponse) {
            request.setAttribute("message", "question null ou Trop de caracteres (max=255)");
            valide = false;
        }
        
        if (valide) {
            // Recupere l'utilisateur dans la session
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpSession session = req.getSession();
            Personne auteur = (Personne)session.getAttribute("user");
            try {
                QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
                Reponse inserted = new Reponse();
                inserted.setIdQuestion(questionId);
                inserted.setIdPersonne(auteur.getId());
                inserted.setLibelle(libelleReponse);
                dao.insertReponse(inserted);
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    request.setAttribute("message", "Cette reponse existe dejà !");
                    valideForm = false;
                } else {
                    request.setAttribute("message", "Problème interne !");
                    valideForm = false;
                }
                ex.printStackTrace();
                Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // redirection
        if (valideForm && valide) {
            request.setAttribute("messageSuccess", "Votre Reponse est enregistrée !");
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
