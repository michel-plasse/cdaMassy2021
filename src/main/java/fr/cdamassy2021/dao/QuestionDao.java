/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.cdamassy2021.model.Question;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author thoma
 */
public class QuestionDao implements Dao<Question> {

    private static final String INSERT = "INSERT INTO question (id_canal,id_createur,libelle,id_type_question) VALUES ( ?, ?, ?, ?);";
    private static final String SELECTBYID = "SELECT * FROM question WHERE id_question=?";
    private static final String TOUS_LES_MEMBRES = "SELECT * FROM question LIMIT ?, ?";
    public QuestionDao() {

    }

    @Override
    public boolean insert(Question inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, inserted.getCanalId());
        stmt.setLong(2, inserted.getIdCreateur());
        stmt.setString(3, inserted.getLibelle());
        stmt.setInt(4, inserted.getType().ordinal());

        stmt.execute();
        // Récupérer le id auto-incrémenté
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Le id est dans la 1ere colonne trouvee
            inserted.setId(rs.getInt(1));
        }
        result = true;
        return result;
    }

    @Override
    public void delete(Question deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question findById(long id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = null;
        Question found = null;
        try {
            preparedStatement = connection.prepareStatement(SELECTBYID);
            preparedStatement.setLong(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                Question.TypeQuestion type
                    = Question.TypeQuestion.values()[res.getInt("id_type_question")];
                found = new Question();
                found.setType(type);
                found.setId(res.getInt("id_question"));
                found.setCanalId(res.getInt("id_canal"));
                found.setIdCreateur(res.getInt("id_createur"));
                found.setLibelle(res.getString("libelle"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

      /**
   * Liste de tous les membres, en paginant à raison de nbElementsParPage par page
   * pour la page n° noPage
   * @param noPage n° de la page à afficher (1ere = 1)
   * @param nbElementsParPage nombre maximal de membres à retourner
   * @return
   * @throws SQLException 
   */
    public static List<Question> getAllWithinLimit(int noPage, int nbElementsParPage) throws SQLException {
      // Mettre en dur le résultat
      List<Question> result = new ArrayList();
      Connection connection = Database.getConnection();
      PreparedStatement stmt = connection.prepareStatement(TOUS_LES_MEMBRES);
      stmt.setInt(1, nbElementsParPage * (noPage - 1));
      stmt.setInt(2, nbElementsParPage);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Question.TypeQuestion type
        = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
        result.add(new Question(
                rs.getInt("id_question"),
                type,
                rs.getInt("id_canal"),
                rs.getInt("id_createur"),
                rs.getString("libelle"),
                null));
      }
        System.out.println(result.toString());
      return result;
    }
    @Override
    public ArrayList<Question> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
