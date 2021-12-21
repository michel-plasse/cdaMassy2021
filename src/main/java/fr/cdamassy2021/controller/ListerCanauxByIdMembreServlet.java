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
import fr.cdamassy2021.model.Personne;
import javax.servlet.http.HttpSession;

/**
*
* @author borel,beatrice,huawei
*/

@WebServlet(name = "ListerCanauxServlet", urlPatterns = {"/mescanaux"})
public class ListerCanauxByIdMembreServlet extends HttpServlet {

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
        //Recupere la session 
        HttpSession session = request.getSession();
        //Recupere l'utilisateur qui a ete ajoute a la session lorsqu'il s'est connecte(voir ConnexionServlet ligne 89)et 
        //on le stocke dans une objet de type personne et 
      
        Personne personne_en_session = (Personne) session.getAttribute("user");
       //On utilise cet objet pour acceder a l'id de l'utilisateur en session et l'envoyer a notre methode
      // je recupere la liste que me renvoi ma Dao
      List<Canal> canaux = CanalDao.getAllByIdPersonne(personne_en_session.getId());      
      // Mettre en post-it les canaux afin de les reccuperer dans la jsp 
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

