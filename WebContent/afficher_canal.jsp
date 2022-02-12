package fr.cdamassy2021.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdamassy2021.dao.CanalDao;
import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.Personne;

/**
 * Servlet implementation class AfficherCanalServlet
 */
@WebServlet("/canal")
public class AfficherCanalServlet extends HttpServlet {
	
	int idCanal=1;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanal);
			request.setAttribute("membres", membresCanal);
			
			/**List<EFG> efgs;
			dao = DaoFactory.getInstance().getCanalDao();
		efgs = dao.getAllEfgsByIdCanal(idCanal);
			request.setAttribute("exercices", efgs);
			System.out.println("Taille :" + efgs.size()); **/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			request.getRequestDispatcher("WEB-INF/canal.jsp").forward(request, response);
	}


}