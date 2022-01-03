package fr.cdamassy2021.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import fr.cdamassy2021.dao.CanalDao;
import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "ActiviteQuestions", urlPatterns = {"/activitequestions"})
public class ActiviteQuestionsServlet extends HttpServlet {

    private final String VUE_OK = "WEB-INF/activitequestions.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

    /**
     * Redirige l'utilisateur vers une vue(activité) de travail, relative à 
     * l'edition, la gestion, l'affichage de Question/Questionnaires/Sondages
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue;
        try {
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpSession session = req.getSession();
            Personne auteur = (Personne)session.getAttribute("user");
            CanalDao cDao = new CanalDao();
            // Les paramètres encore en dur
            List<Canal> canaux = cDao.getAllByIdPersonne(auteur.getId());
            // Mettre en post-it les questions
            session.setAttribute("canauxMembre", canaux);
            vue = VUE_OK;
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
        return " Servlet relative à l'activité d'édition/gestion/affichage des"
                + "Questions/Questionnaires/Sondages.";
    }// </editor-fold>

}
