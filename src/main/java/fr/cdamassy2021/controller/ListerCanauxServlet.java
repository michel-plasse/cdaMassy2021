/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cdamassy2021.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdamassy2021.dao.CanalDao;
import fr.cdamassy2021.model.Canal;

/**
 *
 * @author michel
 */
@WebServlet(name = "ListerCanauxServlet", urlPatterns = {"/canaux"})
public class ListerCanauxServlet extends HttpServlet {

  private final String VUE_OK = "WEB-INF/canaux.jsp";
  private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String vue = VUE_OK;
    try {
      // Les paramètres encore en dur
      List<Canal> canaux = CanalDao.getAllCanaux();
      // Mettre en post-it les membres
      request.setAttribute("canaux", canaux);
    } catch (SQLException exc) {
      vue = VUE_ERREUR;
      request.setAttribute("message", "Pb avec la BD");
      // Journaliser l'exception dans le log de tomcat
      exc.printStackTrace();
    }
    // Passer la main à la vue
    request.getRequestDispatcher(vue).forward(request, response);
  }

}

