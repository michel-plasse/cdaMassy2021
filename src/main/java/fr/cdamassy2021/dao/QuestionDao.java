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
    private static final String INSERT_PROPOSITION = "INSERT INTO proposition (id_question,libelle,est_correcte) VALUES ( ?, ?, ?);";
    private static final String SELECT_BY_QUESTION_ID
            = "SELECT  q.*, p.nom, p.prenom\n"
            + "FROM question q\n"
            + "	INNER JOIN \n"
            + "		personne p \n"
            + "			ON p.id_personne = q.id_createur\n"
            + "WHERE id_question = ?;";
    private static final String SELECT_ALL_QUESTIONS_IN_LIMIT
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n" 
            + "LIMIT ?, ?;";
    private static final String SELECT_PROPOSITIONS_WITH_QUESTION_ID
            = "SELECT * FROM proposition WHERE id_question=?;";
    private static final String SELECT_ALL_BY_CREATOR = ""
            + "SELECT q.*, p.prenom, p.nom"
            + "FROM question q"
            + "     INNER JOIN"
            + "         personne p"
            + "         ON q.id_createur = ?"
            + "LIMIT ?, ?;";

    /**
     * class default constructor
     */
    public QuestionDao() {
    }

    /**
     * Insert une question sans proposition dans la base de donnée
     *
     * @param inserted
     * @return isInsertionOk
     * @throws SQLException
     */
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

    /**
     * Insertion des 'Question' et de sa liste de 'Proposition' sur le modèle
     * transactionnel.<br>
     * Si l'insertion de la question ou de chacune des proposition de la <br>
     * liste échoue la transaction est annulée avec un connection.rollback()<br>
     * <br>
     *
     * @param inserted : question à inserer dans la DB<br>
     * @param propositions : reponses possibles relatives à la question<br>
     * @return isCommit: true si l'insertion s'est passée comme prévu.<br>
     * @throws SQLException <br>
     */
    public boolean insert(Question inserted, List<Proposition> propositions)
            throws SQLException {
        Boolean isCommit = false;
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);

            // Inserer Question: //
            // Compile la requete insert question
            PreparedStatement stmt
                    = connection.prepareStatement(
                            INSERT_QUESTION,
                            Statement.RETURN_GENERATED_KEYS);
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
            //compile la requete insertion proposition
            PreparedStatement stmt1
                    = connection.prepareStatement(
                            INSERT_PROPOSITION,
                            Statement.RETURN_GENERATED_KEYS);
            // Inserer Propositions //
            for (Proposition p : propositions) {
                stmt1.setInt(1, inserted.getId());
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
            isCommit = true;
        } catch (Exception e) {
            System.out.println("error");
            connection.rollback();
            throw e;
        }
        return isCommit;
    }

    /**
     *
     * @param deleted
     */
    @Override
    public void delete(Question deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Renvoit la 'Question' qui a pour id celui passé en paramètre et
     * l'initialise avec la liste de ses 'Proposition' de réponses.
     *
     * @param id de la 'Question' à chercher.
     * @return la 'Question' recherchée.
     * @throws SQLException
     */
    @Override
    public Question findById(long id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = null;
        Question found = null;
        try {
            // Chercher l'ID puis et initialiser l'objet Question
            preparedStatement = connection.prepareStatement(SELECT_BY_QUESTION_ID);
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

                // Chercher les proposition, les intialiser et 
                //ajouter à l'objet question.
                preparedStatement = connection.prepareStatement(
                        SELECT_PROPOSITIONS_WITH_QUESTION_ID);
                preparedStatement.setLong(1, id);
                ResultSet resProps = preparedStatement.executeQuery();
                ArrayList<Proposition> resList = found.getPropositions();
                while (resProps.next()) {
                    Proposition pFound = new Proposition();
                    pFound.setIdProposition(resProps.getInt("id_proposition"));
                    Proposition.Correctness correctness
                            = Proposition.Correctness.values()[resProps.getInt("est_correcte")];
                    pFound.setIsCorrect(correctness);
                    pFound.setIdQuestion(resProps.getInt("id_question"));
                    pFound.setLibelle(resProps.getString("libelle"));
                    resList.add(pFound);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    /**
     * Liste de toutes les questions avec leurs propositions de réponse, en
     * paginant à raison de nbElementsParPage
     *
     * par page pour la page n° noPage
     *
     * @param noPage n° de la page à afficher (1ere = 1)
     * @param nbElementsParPage nombre maximal de questions à retourner
     * @return
     * @throws SQLException
     */
    public static List<Question> getAllWithinLimit(int noPage, int nbElementsParPage) throws SQLException {
        // Mettre en dur le résultat
        List<Question> result = new ArrayList();
        Connection connection = Database.getConnection();
        PreparedStatement selectQuestionStmt = connection.prepareStatement(SELECT_ALL_QUESTIONS_IN_LIMIT);
        PreparedStatement selectPropsStmt = connection.prepareStatement(
                SELECT_PROPOSITIONS_WITH_QUESTION_ID);
        selectQuestionStmt.setInt(1, nbElementsParPage * (noPage - 1));
        selectQuestionStmt.setInt(2, nbElementsParPage);
        ResultSet rs = selectQuestionStmt.executeQuery();
        while (rs.next()) {
            Question.TypeQuestion type
                    = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
            ArrayList<Proposition> propsList = new ArrayList<Proposition>();
            Question found = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom")+" "+rs.getString("nom"),
                    rs.getString("libelle"),
                    propsList);
            result.add(found);
            // Chercher les proposition, les intialiser et 
            //ajouter à l'objet question.
            selectPropsStmt.setLong(1, found.getId());
            ResultSet resProps = selectPropsStmt.executeQuery();
            while (resProps.next()) {
                Proposition pFound = new Proposition();
                pFound.setIdProposition(resProps.getInt("id_proposition"));
                Proposition.Correctness correctness
                        = Proposition.Correctness.values()[resProps.getInt("est_correcte")];
                pFound.setIsCorrect(correctness);
                pFound.setIdQuestion(resProps.getInt("id_question"));
                pFound.setLibelle(resProps.getString("libelle"));
                propsList.add(pFound);
            }
            System.out.println(result.toString());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Question> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
