/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.cdamassy2021.model.Question;
import java.sql.Statement;
import java.util.List;

/**
 * class QuestionDao<br>
 * CRUD Dao : Question et Proposition
 *
 * @author thoma
 */
public class QuestionDao implements Dao<Question> {

    private static final String INSERT_QUESTION = "INSERT INTO question (id_canal,id_createur,libelle,id_type_question) VALUES ( ?, ?, ?, ?);";
    private static final String SELECT_BY_ID = "SELECT * FROM question WHERE id_question=?";
    private static final String ALL_QUESTIONS = "SELECT * FROM question LIMIT ?, ?";
    private static final String INSERT_PROPOSITION = "INSERT INTO proposition (id_question,libelle,est_correcte) VALUES ( ?, ?, ?);";

    public QuestionDao() {
    }

    @Override
    public boolean insert(Question inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
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

    /*
    *  Transaction : Si l'insertion de Question 
    * ou de chacune des Proposition de la liste échoue
    * la transaction est annulée et la connection.rollback()
    *  
     */
    public boolean insert(Question insertedQuestion, List<Proposition> propositions)
            throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);

            // Inserer Question: //
            // Compile la requete insert question
            PreparedStatement stmt
                    = connection.prepareStatement(
                            INSERT_QUESTION,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, insertedQuestion.getCanalId());
            stmt.setLong(2, insertedQuestion.getIdCreateur());
            stmt.setString(3, insertedQuestion.getLibelle());
            stmt.setInt(4, insertedQuestion.getType().ordinal());
            stmt.execute();
            // Récupérer le id auto-incrémenté
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Le id est dans la 1ere colonne trouvee
                insertedQuestion.setId(rs.getInt(1));
            }

            // Inserer Propositions //
            for (Proposition p : propositions) {
                //compile la requete insertion proposition
                PreparedStatement stmt1
                        = connection.prepareStatement(
                                INSERT_PROPOSITION,
                                Statement.RETURN_GENERATED_KEYS);
                stmt1.setInt(1, insertedQuestion.getId());
                stmt1.setString(2, p.getLibelle());
                //Store la valeur de l'enum Correctness en DB.
                Proposition.Correctness correctness = p.getIsCorrect();
                stmt1.setInt(3, correctness.ordinal());
                stmt1.execute();
                // Récupérer le id auto-incrémenté
                ResultSet rs2 = stmt1.getGeneratedKeys();
                if (rs2.next()) {
                    // Le id est dans la 1ere colonne trouvee
                    p.setIdProposition(rs2.getInt(1));
                }
            }
            connection.commit();
        } catch (Exception e) {
            System.out.println("error");
            connection.rollback();
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
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
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
     * Liste de tous les membres, en paginant à raison de nbElementsParPage par
     * page pour la page n° noPage
     *
     * @param noPage n° de la page à afficher (1ere = 1)
     * @param nbElementsParPage nombre maximal de membres à retourner
     * @return
     * @throws SQLException
     */
    public static List<Question> getAllWithinLimit(int noPage, int nbElementsParPage) throws SQLException {
        // Mettre en dur le résultat
        List<Question> result = new ArrayList();
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(ALL_QUESTIONS);
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
