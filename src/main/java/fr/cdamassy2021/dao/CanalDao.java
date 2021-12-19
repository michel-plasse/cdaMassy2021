package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;

public class CanalDao {
	 private static final String TOUS_LES_CANAUX =
	          "SELECT * FROM canal ORDER BY id_canal";

	  /**
	   * Canal d'id et de nom donnés.
	   * Classe le resultat par id et renvoie null si pas trouvé
	   * @param idCanal
	   * @param nom
	   * @return
	   * @throws SQLException
	   */
	  public static List<Canal> getAllCanaux() throws SQLException {
	    // Mettre en dur le résultat
		  /*
		    List<Canal>canaux = new ArrayList<Canal>();
		    Canal c1 = new Canal();
	        Canal c2 = new Canal();
	        Canal c3 = new Canal();
	        Canal c4 = new Canal();
	        c1.setId_canal(1);
	        c1.setNom("CDA");
	        c2.setId_canal(2);
	        c2.setNom("BTS");
	        c3.setId_canal(3);
	        c3.setNom("DUT");
	        c4.setId_canal(4);
	        c4.setNom("AIT");
	        canaux.add(c1);
	        canaux.add(c2);
	        canaux.add(c3);
	        canaux.add(c4);
	        return canaux;
	       */
		  
	    List<Canal>canaux = new ArrayList<Canal>();
	    Connection connection = Database.getConnection();
	    PreparedStatement stmt = connection.prepareStatement(TOUS_LES_CANAUX);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	canaux.add(new Canal(
	              rs.getInt("id_canal"),
	              rs.getString("nom")
	              ));
	    }
	    return canaux;
	  }

}
