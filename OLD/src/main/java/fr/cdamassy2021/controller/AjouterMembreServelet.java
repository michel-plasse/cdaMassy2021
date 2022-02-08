package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.CanalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BBH
 */
@WebServlet(name = "AjouterMembreServelet", urlPatterns = {"/ajouterMembre"})
public class AjouterMembreServelet extends HttpServlet {

    private final String VUE_OK = "/membrescanal";
    private final String VUE_ERREUR = "/WEB-INF/erreur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // cree un vue
        String vue = VUE_OK;
        
        //recupere idPersonne, idCanal
        int idPersonneAjouter       =       Integer.parseInt(request.getParameter("idPersonneAjouter")) ;
        int idCanal                 =       Integer.parseInt(request.getParameter("idCanalAjouter"));
        
        try {
            int estAjouterAuCanal = CanalDao.AjouterMembreAuCanal(idPersonneAjouter, idCanal);
            request.setAttribute("idCanal", idCanal);
            response.sendRedirect(request.getServletContext().getContextPath()+vue+"?idCanal="+idCanal);
        } catch (SQLException e) {
            vue = VUE_ERREUR;
            request.setAttribute("message", "pb avec la base de donnée");
            e.printStackTrace();
            request.getRequestDispatcher(VUE_ERREUR).forward(request, response);
        }

    }


}
