package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.CanalDao;
import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author beaad
 */
@WebServlet(name = "SupprimerMembreServlet", urlPatterns = {"/enleverMembre"})
public class SupprimerMembreServlet extends HttpServlet {

    private final String URL_OK = "/membrescanal";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idMembreAEffacer = Integer.parseInt(request.getParameter("idMembreAEffacer"));
        int idCanal = Integer.parseInt(request.getParameter("idCanal"));

        try {
            int estRetirerDuCanal = CanalDao.SupprimerMembreDuCanal(idMembreAEffacer, idCanal);
            request.setAttribute("idCanal", idCanal);
            response.sendRedirect(request.getServletContext().getContextPath() + URL_OK + "?idCanal=" + idCanal);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            request.setAttribute("message", "pb avec la base de donnée");
            e.printStackTrace();
            request.getRequestDispatcher(VUE_ERREUR).forward(request, response);
        }
    }

}
