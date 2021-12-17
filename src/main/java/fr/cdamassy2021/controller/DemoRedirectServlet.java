/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette Servet Demo présente une redirection entre differentes Servlets<br>
 * 
 * Exemple d'application: Une redirection directe entre servlet nous permet<br>
 * entre autre d'empêcher un double appel d'une sevlet d'insertion lorsqu'un <br>
 * utilisateur actualise sa page web depuis son navigateur web.<br>

 * @author thoma
 */
@WebServlet(name = "DemoRedirectServlet", urlPatterns = {"/DemoRedirect"})
public class DemoRedirectServlet extends HttpServlet {

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
        // Faire une operation à risque pour la DB
        // Dao.insert(membre)
        // Rediriger l'utilisateur pour l'empecher de pouvoir renvoyer cette requete
        response.sendRedirect(request.getServletContext().getContextPath()+"/membres");
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet présentant la "
                + "redirection entre deux Servlets";
    }// </editor-fold>
    
}
