/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.model.Personne;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author michel
 */
@WebServlet(name = "ListerMembresServlet", urlPatterns = {"/membres"})
public class ListerMembresServlet extends HttpServlet {

  private final String VUE = "WEB-INF/membres.jsp";
          
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
    // Mettre en dur le résultat
    List<Personne> membres = new ArrayList();
    membres.add(new Personne(1, "prenom", "nom", "email", "tel", "pwd"));
    // Mettre en post-it les membres
    request.setAttribute("membres", membres);
    // Passer la main à la vue
    request.getRequestDispatcher(VUE).forward(request, response);
  }

}
