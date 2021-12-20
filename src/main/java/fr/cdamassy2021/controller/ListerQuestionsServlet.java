package fr.cdamassy2021.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Question;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thoma
 */
@WebServlet(name = "ListerMQuestionsServlet", urlPatterns = {"/questions"})
public class ListerQuestionsServlet extends HttpServlet {

  private final String VUE_OK = "WEB-INF/questions.jsp";
  private final String VUE_ERREUR = "WEB-INF/erreur.jsp";
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String vue = VUE_OK;
    try {
      // Les paramètres encore en dur
              System.out.println("TRY");
      List<Question> questions = QuestionDao.getAllWithinLimit(1, 10);
                    System.out.println("OK1");
      // Mettre en post-it les questions
      request.setAttribute("questions", questions);
              System.out.println("OK2");
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
