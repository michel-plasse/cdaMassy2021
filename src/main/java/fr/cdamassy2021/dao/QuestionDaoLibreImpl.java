/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thoma
 */
public class QuestionDaoLibreImpl extends QuestionDao {

    /**
     * class default constructor
     */
    public QuestionDaoLibreImpl() {
    }

    /**
     * Insere newQuestion dans la base de donnée et positionne newQuestion.id à
     * la valeur auto-incrémentée dans la base.
     *
     * @param newQuestion est le bean à inserer.
     * @return isInsertionOk est le résultat de l'opération
     * @throws SQLException
     */
    @Override
    public boolean insert(Question newQuestion) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(
                INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, newQuestion.getCanalId());
        stmt.setLong(2, newQuestion.getIdCreateur());
        stmt.setString(3, newQuestion.getLibelle());
        stmt.setInt(4, newQuestion.getType().ordinal());

        stmt.execute();
        // Récupérer le id auto-incrémenté
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Le id est dans la 1ere colonne trouvee
            newQuestion.setId(rs.getInt(1));
        }
        result = true;
        return result;
    }

    /**
     * Renvoit la 'Question' qui a pour id searchedId, initialisé avec la liste
     * complête des 'Propositions' de réponses qui lui sont associées.
     *
     * @param searchedId est la clef à trouver dans la base de données.
     * @return le bean 'Question' recherché.
     * @throws SQLException
     */
    @Override
    public Question findById(long searchedId) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = null;
        Question found = null;
        try {
            // Chercher l'ID puis et initialiser l'objet Question
            preparedStatement = connection.prepareStatement(SELECT_BY_QUESTION_ID);
            preparedStatement.setLong(1, searchedId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                // recupère son type sous forme d'enum
                Question.TypeQuestion type
                        = Question.TypeQuestion
                                .values()[res.getInt("id_type_question")];
                // initialiseation de la question 
                found = new Question();
                found.setType(type);
                found.setId(res.getInt("id_question"));
                found.setCanalId(res.getInt("id_canal"));
                found.setIdCreateur(res.getInt("id_createur"));
                found.setLibelle(res.getString("libelle"));
            }
        } catch (SQLException e) {
        }
        // retourne un bean initialisé.
        return found;
    }

    /**
     * Renvoit une liste de toutes les 'Question' en paginant à raison de
     * nbElementsParPages par pages pour la page n° noPage.
     *
     * Chaque 'Question' est initialisée avec la liste de 'Propositions' de
     * réponses qui lui est associée.
     *
     * @param noPage n° de la page à afficher (1ere = 1)
     * @param nbElementsParPage nombre maximal de questions à retourner
     * @return
     * @throws SQLException
     *
     */
    public ArrayList<Question> getAllPaging(
            int noPage, int nbElementsParPage) throws SQLException {
        Connection connection = Database.getConnection();
        ArrayList<Question> result = new ArrayList();
        // prepare questions selection
        PreparedStatement selectQuestionStmt
                = connection.prepareStatement(
                        SELECT_ALL_QUESTIONS_IN_LIMIT);
        selectQuestionStmt.setInt(1, nbElementsParPage * (noPage - 1));
        selectQuestionStmt.setInt(2, nbElementsParPage);
        ResultSet rs = selectQuestionStmt.executeQuery();
        // Pour chaque question trouvée:
        while (rs.next()) {
            // convert int to enum of TypeQuestion
            Question.TypeQuestion type
                    = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
            ArrayList<Proposition> propsList = new ArrayList<Proposition>();
            // Initialiser le bean 
            Question found = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"),
                    propsList);
            result.add(found);
            System.out.println(result.toString());
        }
        //retourner la liste de beans Questions fournit par la BDD.
        return result;
    }

    /**
     * Renvoit une liste de toutes les 'Question' appartenant au canal designé
     * par idCanal en paginant à raison de nbElementsParPages par pages pour la
     * page n° noPage.
     *
     * Chaque 'Question' est initialisée avec la liste de 'Propositions' de
     * réponses qui lui est associée.
     *
     * @param noPage n° de la page à afficher (1ere = 1)
     * @param nbElementsParPage nombre maximal de questions à retourner
     * @return
     * @throws SQLException
     *
     */
    @Override
    public ArrayList<Question> getAllByCanalPaging(int idCanal, int noPage, int nbElementsParPage) throws SQLException {
        Connection connection = Database.getConnection();
        ArrayList<Question> result = new ArrayList();
        // prepare questions selection
        PreparedStatement selectQuestionStmt
                = connection.prepareStatement(
                        SELECT_ALL_QUESTIONS_BY_CANAL_ID);
        selectQuestionStmt.setInt(1, idCanal);
        selectQuestionStmt.setInt(2, nbElementsParPage * (noPage - 1));
        selectQuestionStmt.setInt(3, nbElementsParPage);
        ResultSet rs = selectQuestionStmt.executeQuery();
        // Pour chaque question trouvée:
        while (rs.next()) {
            // convert int to enum of TypeQuestion
            Question.TypeQuestion type
                    = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
            ArrayList<Proposition> propsList = new ArrayList<Proposition>();
            // Initialise un nouveeau bean Question
            Question found = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"),
                    propsList);
            result.add(found);
            System.out.println(result.toString());
        }
        //retourner la liste de beans Questions fournit par la BDD.
        return result;
    }

    /**
     * not supported yet *
     */
    @Override
    public void delete(Question deleted) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * not supported yet *
     */
    @Override
    public ArrayList<Question> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
