/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.IQuestion;
import fr.cdamassy2021.model.QuestionFactory;
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
public class QuestionDao implements Dao<IQuestion> {

    private final String INSERT = "INSERT INTO question (canal_id,auteur_id,enonce,type_reponses) VALUES ( ?, ?, ?, ?);";
    private final String SELECTBYID = "SELECT * FROM question WHERE id=?";

    public QuestionDao() {

    }

    @Override
    public boolean insert(IQuestion inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, inserted.getCanalId());
        stmt.setLong(2, inserted.getAuteurId());
        stmt.setString(3, inserted.getStatement());
        stmt.setInt(4, inserted.getType().ordinal());

        stmt.execute();
        // Récupérer le id auto-incrémenté
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Le id est dans la 1ere colonne trouvee
            inserted.setId((long) rs.getInt(1));
        }
        result = true;
        return result;
    }

    @Override
    public void delete(IQuestion deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IQuestion findById(long id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = null;
        IQuestion found = null;
        try {
            preparedStatement = connection.prepareStatement(SELECTBYID);
            preparedStatement.setLong(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                IQuestion.TypeQuestion type
                    = IQuestion.TypeQuestion.values()[res.getInt("type_reponses")];
                QuestionFactory qFactory = new QuestionFactory();
                try {
                    IQuestion question = qFactory.createQuestion(type);
                } catch (Exception e) {
                    System.out.println(e);
                }

                found.setType(type);
                found.setId(res.getLong("id"));
                found.setCanalId(res.getLong("canal_id"));
                found.setAuteurId(res.getLong("auteur_id"));
                found.setStatement(res.getString("enonce"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public ArrayList<IQuestion> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
