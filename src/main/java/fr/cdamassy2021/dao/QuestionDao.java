/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Proposition;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * class QuestionDao<br>
 * 
 * eng: This Dao is responsible for inserting and retrieving 'Question' beans from the DB
 * and initialise those with their respective Proposition beans list.<br>
 * For this reason it also provide those operations with the 'Proposition' beans.<br>
 * <br>
 * fr: Cette Dao a pour résponsabilité d'insérer dans la BDD, et d'en extraire<br>
 * les beans 'Question' initialisés avec leur liste de Propositions respectives.<br>
 * Pour ce faire, elle assure aussi ces opérations avec avec les beans <br>
 * 'Proposition'.
 *
 * @author Kamal, Thomas
 */
public class QuestionDao implements Dao<Question> {

    private static final String INSERT_QUESTION
            = "INSERT INTO question ("
            + "id_canal,id_createur,libelle,id_type_question) "
            + "VALUES ( ?, ?, ?, ?);";

    private static final String INSERT_PROPOSITION = "INSERT INTO proposition ("
            + "id_question,libelle,est_correcte)"
            + " VALUES ( ?, ?, ?);";

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

    private static final String SELECT_ALL_BY_CREATOR_ID = ""
            + "SELECT q.*, p.prenom, p.nom"
            + "FROM question q"
            + "     INNER JOIN"
            + "         personne p"
            + "         ON q.id_createur = ?"
            + "LIMIT ?, ?;";

    private static final String SELECT_ALL_QUESTIONS_BY_CANAL_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "WHERE id_canal=?\n"
            + "LIMIT ?, ?;";

    private static final String SELECT_ALL_QUESTIONS_BY_QUESTIONNAIRE_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "WHERE id_questionnaire=?\n"
            + "LIMIT ?, ?;";

    private static final String SELECT_PROPOSITIONS_WITH_QUESTION_ID
            = "SELECT * FROM proposition WHERE id_question=?;";

    /**
     * class default constructor
     */
    public QuestionDao() {
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
        PreparedStatement stmt = connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
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

                // Chercher les proposition associées à found,
                preparedStatement = connection.prepareStatement(
                        SELECT_PROPOSITIONS_WITH_QUESTION_ID);
                preparedStatement.setLong(1, searchedId);
                ResultSet resProps = preparedStatement.executeQuery();
                ArrayList<Proposition> resList = found.getPropositions();
                // et pour chacune d'elles, l'ajouter à sa liste.
                while (resProps.next()) {
                    Proposition pFound = new Proposition();
                    pFound.setIdProposition(resProps.getInt("id_proposition"));
                    Proposition.Correctness correctness
                            = Proposition.Correctness
                                    .values()[resProps.getInt("est_correcte")];
                    pFound.setIsCorrect(correctness);
                    pFound.setIdQuestion(resProps.getInt("id_question"));
                    pFound.setLibelle(resProps.getString("libelle"));
                    resList.add(pFound);
                }
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
            // prepare propositions selection
            PreparedStatement selectPropsStmt
                    = connection.prepareStatement(
                            SELECT_PROPOSITIONS_WITH_QUESTION_ID);
            // Chercher les propositions
            selectPropsStmt.setLong(1, found.getId());
            ResultSet resProps = selectPropsStmt.executeQuery();
            // Pour chaque propositions trouvée:
            while (resProps.next()) {
                Proposition pFound = new Proposition();
                // Initialiser le bean
                pFound.setIdProposition(resProps.getInt("id_proposition"));
                Proposition.Correctness correctness
                        = Proposition.Correctness.values()[resProps.getInt(
                        "est_correcte")];
                pFound.setIsCorrect(correctness);
                pFound.setIdQuestion(resProps.getInt("id_question"));
                pFound.setLibelle(resProps.getString("libelle"));
                // L'ajouter à la liste de propositions
                propsList.add(pFound);
            }
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
