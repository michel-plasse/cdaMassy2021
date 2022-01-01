/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.service.QuestionService;
import java.io.IOException;
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
@WebServlet(name = "repondreQuestions", urlPatterns = {"/repondreQuestions"})
public class RepondreQuestionsServlet extends HttpServlet {
        
    private final String VUE_OK = "WEB-INF/repondreQuestions.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";
    
    /**
     * Redirige l'utilisateur vers l'afficage des questions auxquelles il peut repondre
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue = VUE_OK;
        try {
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpSession session = req.getSession();
            Personne user = (Personne)session.getAttribute("user");
            QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
            int canalId = Integer.parseInt(request.getParameter("canalChoisi"));
            ArrayList<Question> questions = dao.getAllPendingQuestions(user.getId(), canalId);
            session.setAttribute("pendingQuestions", questions);
        } catch (SQLException exc) {
            vue = VUE_ERREUR;
            request.setAttribute("message", "Pb avec la BD");

            // Journaliser l'exception dans le log de tomcat
            exc.printStackTrace();
        }
        // Passer la main à la vue
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
