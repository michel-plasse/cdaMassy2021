/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author thoma
 */
public class QuestionDao implements Dao<Question> {

    public QuestionDao() {
        
    }

    
    @Override
    public boolean insert(Question inserted) throws SQLException{
    Boolean result = false;
    Connection connection = Database.getConnection();
    String insert = "INSERT INTO question (canal_id,auteur_id,enonce,type_reponses) VALUES ( ?, ?, ?, ?);";
    //compile la requete
    PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

    stmt.setLong(1, inserted.getCanalId());
    stmt.setLong(2, inserted.getAuteurId());
    stmt.setString(3, inserted.getStatement());
    stmt.setInt(4, inserted.getType().ordinal());

    stmt.execute();
    // Récupérer le id auto-incrémenté
        ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) {
      // Le id est dans la 1ere colonne trouvee
      inserted.setId((long)rs.getInt(1));
    }
        result = true;
        return result;
    }

    @Override
    public void delete(Question deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Question> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
