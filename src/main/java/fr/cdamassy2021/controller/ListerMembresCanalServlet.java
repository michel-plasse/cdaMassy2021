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
import fr.cdamassy2021.model.Personne;

/**
*
* @author borel,beatrice,huawei
*/
/**
 * Servlet implementation class ListerMembresCanalServlet
 */
@WebServlet(name = "ListerMembresCanalServlet",urlPatterns = { "/membrescanal" })
public class ListerMembresCanalServlet extends HttpServlet {

	  private final String VUE_OK = "WEB-INF/membres.jsp";
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
	    	int idCanal = Integer.parseInt(request.getParameter("idCanal"));
	      // je reccupere la liste que me renvoi ma Dao
	      List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanal);
	      // Mettre en post-it les membres du canal concerné pour les reccuperer dans la jsp 
	      request.setAttribute("membres", membresCanal);
	    } catch (SQLException exc) {
	      vue = VUE_ERREUR;
	      
	      request.setAttribute("message", exc.getMessage());
	      // Journaliser l'exception dans le log de tomcat
	      exc.printStackTrace();
	    }
	    // Passer la main à la vue
	    request.getRequestDispatcher(vue).forward(request, response);
	  }

	}
