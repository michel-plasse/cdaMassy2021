/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.EFGDao;
import fr.cdamassy2021.model.EFG;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Florian
 */
@WebServlet(name = "AfficheEFGCree", urlPatterns = {"/AfficheEFG"})
public class AfficheEFGCree extends HttpServlet {

    private final String VUE_OK = "WEB-INF/afficherEFGCree.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idcanal = (int) session.getAttribute("Canal");
        int idcreateur = 1;
        String intitule = request.getParameter("Intitulé");
        EFG nouvelEFG = new EFG(0, idcreateur, idcanal, intitule);
        EFGDao instance = new EFGDao();
        try {
            boolean inseré = instance.insert(nouvelEFG);
            if (inseré) {
                request.setAttribute("EFGcree", nouvelEFG);
                request.getRequestDispatcher(VUE_OK).forward(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("message", "erreur sql");
            request.getRequestDispatcher(VUE_ERREUR).forward(request, response);
            ex.printStackTrace();
        }
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
