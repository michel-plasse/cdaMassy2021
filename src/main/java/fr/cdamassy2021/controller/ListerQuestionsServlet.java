package fr.cdamassy2021.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import fr.cdamassy2021.dao.QuestionDaoImpl;
import fr.cdamassy2021.model.Question;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdamassy2021.dao.IDao;

/**
 *
 * @author thoma
 */
@WebServlet(name = "ListerMQuestionsServlet", urlPatterns = {"/questions"})
public class ListerQuestionsServlet extends HttpServlet {

    private final String VUE_OK = "WEB-INF/questions.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

    /**
     * Recupere la liste de 10 questions specifiée par l'utilisateur avec noPage
     * et redirige vers questions.jsp pour les y afficher.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IDao dao = new QuestionDaoImpl();
        String vue = VUE_OK;
        int pageRequest =1;
        String page = request.getParameter("noPage");
        if (page != null){
           pageRequest = Integer.parseInt(page);
        }
        pageRequest = (pageRequest==0)?1: pageRequest;
        try {
            // Les paramètres encore en dur
            ArrayList<Question> questions = dao.getAllPaging(pageRequest, 10);
            // Mettre en post-it les questions
            request.setAttribute("questions", questions);
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
