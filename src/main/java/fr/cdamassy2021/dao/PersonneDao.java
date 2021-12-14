package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michel
 */
public class PersonneDao {
  private static final String TOUS_LES_MEMBRES =
          "SELECT * FROM personne LIMIT ?, ?";

  /**
   * Personne de login et password donnés. Renvoie null si pas trouvé.
   *
   * @param login
   * @param password
   * @return
   */
  public static Personne getByLoginPassword(String login, String password) throws SQLException {
    Personne result = null;
    Connection connection = Database.getConnection();
    String sql = "SELECT * FROM personne WHERE email=? AND pwd=?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, login);
    stmt.setString(2, password);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      result = new Personne(
              rs.getInt("id_personne"),
              rs.getString("prenom"),
              rs.getString("nom"),
              rs.getString("email"),
              rs.getString("tel"),
              null); // pas de mot de passe en mémoire
    }
    return result;
  }

  /**
   * Liste de tous les membres, en paginant à raison de nbElementsParPage par page
   * pour la page n° noPage
   * @param noPage n° de la page à afficher (1ere = 1)
   * @param nbElementsParPage nombre maximal de membres à retourner
   * @return
   * @throws SQLException 
   */
  public static List<Personne> getTousMembres(int noPage, int nbElementsParPage) throws SQLException {
    // Mettre en dur le résultat
    List<Personne> result = new ArrayList();
    Connection connection = Database.getConnection();
    PreparedStatement stmt = connection.prepareStatement(TOUS_LES_MEMBRES);
    stmt.setInt(1, nbElementsParPage * (noPage - 1));
    stmt.setInt(2, nbElementsParPage);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Personne(
              rs.getInt("id_personne"),
              rs.getString("prenom"),
              rs.getString("nom"),
              rs.getString("email"),
              rs.getString("tel"),
              null));
    }
    return result;
  }

  /**
   * Insere personne dans la base et positionne personne.id à la valeur
   * auto-incrémentée dans la base.
   *
   * @param personne
   * @throws SQLException
   */
  public void insert(Personne personne) throws SQLException {
    Connection connection = Database.getConnection();
    String insert = "INSERT INTO personne (prenom, nom, email, pwd, tel, inscrit_a) VALUES ( ?, ?, ?, ?,?, NOW());";
    //compile la requete
    PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

    stmt.setString(1, personne.getPrenom());
    stmt.setString(2, personne.getNom());
    stmt.setString(3, personne.getEmail());
    stmt.setString(4, personne.getPwd());
    stmt.setString(5, personne.getTel());

    stmt.execute();
    // Récupérer le id auto-incrémenté
    ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) {
      // Le id est dans la 1ere colonne trouvee
      personne.setId(rs.getInt(1));
    }
  }
}
