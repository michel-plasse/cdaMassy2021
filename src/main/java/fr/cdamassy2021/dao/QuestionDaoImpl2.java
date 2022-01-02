package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Reponse;
import fr.cdamassy2021.model.Sondage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * eng: This IDao is responsible for inserting and retrieving 'Question' beans
 * from the DB and initialise those with their respective Proposition beans
 * list.<br>
 * For this reason it also provide those operations with the 'Proposition'
 * beans.<br>
 * <br>
 * fr: Cette IDao a pour résponsabilité d'insérer dans la BDD, et d'en
 * extraire<br>
 * les beans 'Question' initialisés avec leur liste de Propositions
 * respectives.<br>
 * Pour ce faire, elle assure aussi ces opérations avec avec les beans
 * <br>
 * 'Proposition'.
 *
 * @author Kamal, Thomas
 */
public class QuestionDaoImpl2 implements QuestionDao {

    private DaoFactory daoFactory;

    protected static final String INSERT_REPONSE
            = "INSERT INTO reponse ("
            + "id_question,id_personne,libelle) "
            + "VALUES ( ?, ?, ?);";

    protected static final String INSERT_QUESTION
            = "INSERT INTO question ("
            + "id_canal,id_createur,libelle,id_type_question) "
            + "VALUES ( ?, ?, ?, ?);";

    protected static final String SELECT_QUESTION_BY_ID
            = "SELECT  q.*, p.nom, p.prenom\n"
            + "FROM question q\n"
            + "	INNER JOIN \n"
            + "		personne p \n"
            + "			ON p.id_personne = q.id_createur\n"
            + "WHERE id_question = ?;";

    protected static final String SELECT_PROPOSITION_BY_ID
            = "SELECT *\n"
            + "FROM proposition\n"
            + "WHERE id_proposition = ?;";

    protected static final String SELECT_ALL_QUESTIONS_IN_LIMIT
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "LIMIT ?, ?;";

    protected static final String SELECT_ALL_BY_CREATOR_ID = ""
            + "SELECT q.*, p.prenom, p.nom"
            + "FROM question q"
            + "     INNER JOIN"
            + "         personne p"
            + "         ON q.id_createur = ?"
            + "LIMIT ?, ?;";

    protected static final String SELECT_ALL_QUESTIONS_BY_CANAL_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "WHERE id_canal=?\n"
            + "LIMIT ?, ?;";

    protected static final String SELECT_ALL_QUESTIONS_BY_QUESTIONNAIRE_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "WHERE id_questionnaire=?\n"
            + "LIMIT ?, ?;";

    private static final String INSERT_PROPOSITION = "INSERT INTO proposition ("
            + "id_question,libelle,est_correcte)"
            + " VALUES ( ?, ?, ?);";

    private static final String SELECT_PROPOSITIONS_WITH_QUESTION_ID
            = "SELECT * FROM proposition WHERE id_question=?;";

    private static final String SELECT_REPONSES_WITH_QUESTION_ID
            = "SELECT r.*, p.prenom, p.nom\n"
            + "FROM reponse r\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON r.id_personne = p.id_personne\n"
            + "WHERE id_question=?\n;";

    private static final String SELECT_ALL_QUESTIONS_BY_PERSONNE_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN\n"
            + "		personne p\n"
            + "			ON q.id_createur = p.id_personne\n"
            + "WHERE id_personne=?\n"
            + "LIMIT ?, ?;";

    private static final String SELECT_ALL_PENDING_QUESTIONS_BY_PERSONNE_ID_AND_CANAL_ID
            = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN \n"
            + "		personne p\n"
            + "			ON p.id_personne = q.id_createur\n"
            + "WHERE NOT EXISTS(\n"
            + "	SELECT r.id_question\n"
            + "    FROM reponse r\n"
            + "    WHERE r.id_question = q.id_question AND r.id_personne = ?\n" 
            + ") AND id_canal = ?;";

    /**
     * class default constructor
     */
    public QuestionDaoImpl2(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
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
    public int insert(Question newQuestion) throws SQLException {
        Connection connection = daoFactory.getConnection();
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
        int idAttribue = -1;
        if (rs.next()) {
            // Le id est dans la 1ere colonne trouvee
            idAttribue = rs.getInt(1);
            newQuestion.setId(idAttribue);
        }
        return idAttribue;
    }

    /**
     * Insertion des 'Question' et de sa liste de 'Proposition' sur le modèle
     * transactionnel.<br>
     * Si l'insertion de la question ou de chacune des proposition de la
     * <br>
     * liste échoue la transaction est annulée avec un connection.rollback()<br>
     * <br>
     *
     * @param inserted : question à inserer dans la DB<br>
     * @param propositions : reponses possibles relatives à la question<br>
     * @return isCommit: true si l'insertion s'est passée comme prévu.<br>
     * @throws SQLException <br>
     */
    @Override
    public boolean insert(Question inserted, List<Proposition> propositions)
            throws SQLException {
        Boolean isCommit = false;
        Connection connection = daoFactory.getConnection();
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
                Proposition.Correctness correctness = p.getCorrectness();
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
        Connection connection = daoFactory.getConnection();
        PreparedStatement preparedStatement = null;
        Question found = null;

        try {
            // Chercher l'ID puis et initialiser l'objet Question
            preparedStatement = connection.prepareStatement(SELECT_QUESTION_BY_ID);
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
                assignerPropositions(connection, found);
                assignerReponses(connection, found);
            }

        } catch (SQLException e) {
        }
        // retourne un bean initialisé.
        return found;
    }

    /**
     * Renvoit la 'Proposition' qui a pour id searchedId.
     *
     * @param searchedId est la clef à trouver dans la base de données.
     * @return le bean 'Question' recherché.
     * @throws SQLException
     */
    public Proposition findPropositionById(long searchedId) throws SQLException {
        Connection connection = daoFactory.getConnection();
        PreparedStatement preparedStatement = null;
        Proposition found = null;

        try {
            // Chercher l'ID puis et initialiser l'objet Question
            preparedStatement = connection.prepareStatement(SELECT_PROPOSITION_BY_ID);
            preparedStatement.setLong(1, searchedId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                // recupère son type sous forme d'enum
                Proposition.Correctness estCorrect
                        = Proposition.Correctness
                                .values()[res.getInt("est_correcte")];
                // initialiseation de la question 
                found = new Proposition();
                found.setCorrectness(estCorrect);
                found.setIdProposition(res.getInt("id_proposition"));
                found.setIdQuestion(res.getInt("id_question"));
                found.setLibelle("libelle");
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
    @Override
    public ArrayList<Question> getAllPaging(
            int noPage, int nbElementsParPage) throws SQLException {
        Connection connection = daoFactory.getConnection();
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
            // Initialiser le bean 
            Question questionFound = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"));
            assignerPropositions(connection, questionFound);
            assignerReponses(connection, questionFound);
            result.add(questionFound);
        }
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
        Connection connection = daoFactory.getConnection();
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
            // Initialise un nouveeau bean Question
            Question found = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"));
            assignerPropositions(connection, found);
            assignerReponses(connection, found);
            result.add(found);
        }
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

    @Override
    public ArrayList<Question> getAllByIdMembrePaging(
            int idMembre, int noPage, int nbElementsParPage)
            throws SQLException {
        Connection connection = daoFactory.getConnection();
        ArrayList<Question> result = new ArrayList();
        // prepare questions selection
        PreparedStatement selectQuestionStmt
                = connection.prepareStatement(SELECT_ALL_QUESTIONS_BY_PERSONNE_ID);
        selectQuestionStmt.setInt(1, idMembre);
        selectQuestionStmt.setInt(2, nbElementsParPage * (noPage - 1));
        selectQuestionStmt.setInt(3, nbElementsParPage);
        ResultSet rs = selectQuestionStmt.executeQuery();
        // Pour chaque question trouvée:
        while (rs.next()) {
            // convert int to enum of TypeQuestion
            Question.TypeQuestion type
                    = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
            // Initialise un nouveeau bean Question
            Question found = new Question(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"));
            assignerPropositions(connection, found);
            assignerReponses(connection, found);
            result.add(found);
            System.out.println(result.toString());
        }
        //retourner la liste de beans Questions fournit par la BDD.
        return result;
    }

    private void assignerPropositions(Connection con, Question q) throws SQLException {
        try {
            PreparedStatement selectPropsStmt
                    = con.prepareStatement(SELECT_PROPOSITIONS_WITH_QUESTION_ID);
            selectPropsStmt.setLong(1, q.getId());
            ResultSet resProps = selectPropsStmt.executeQuery();
            ArrayList<Proposition> propsList = q.getPropositions();
            // Pour chaque propositions trouvée:
            while (resProps.next()) {
                Proposition pFound = new Proposition();
                // Initialiser le bean
                pFound.setIdProposition(resProps.getInt("id_proposition"));
                Proposition.Correctness correctness
                        = Proposition.Correctness.values()[resProps.getInt(
                        "est_correcte")];
                pFound.setCorrectness(correctness);
                pFound.setIdQuestion(resProps.getInt("id_question"));
                pFound.setLibelle(resProps.getString("libelle"));
                // L'ajouter à la liste de propositions de l'objet Question
                propsList.add(pFound);
            }
        } catch (Exception e) {
        }
    }

    private void assignerReponses(Connection con, Question q) throws SQLException {
        try {
            PreparedStatement preparedStatement2 = con.prepareStatement(
                    SELECT_REPONSES_WITH_QUESTION_ID);
            preparedStatement2.setLong(1, q.getId());
            ResultSet rsReponses = preparedStatement2.executeQuery();
            ArrayList<Reponse> resultList = q.getReponses();
            while (rsReponses.next()) {
                Reponse rFound = new Reponse();
                rFound.setIdPersonne(rsReponses.getInt("id_personne"));
                rFound.setIdQuestion(rsReponses.getInt("id_question"));
                rFound.setLibelle(rsReponses.getString("libelle"));
                rFound.setNomPersonne(rsReponses.getString("prenom") + " " + rsReponses.getString("nom"));
                // L'ajouter à la liste de reponses de l'objet Question
                resultList.add(rFound);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public ArrayList<Sondage> getAllSondagesPaging(int noPage, int nbElementsParPage)
            throws SQLException {
        Connection connection = daoFactory.getConnection();
        ArrayList<Sondage> result = new ArrayList();
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
            // Initialiser le bean 
            Sondage sondageFound = new Sondage(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"));
            assignerPropositions(connection, sondageFound);
            assignerReponses(connection, sondageFound);
            sondageFound.setResults();
            result.add(sondageFound);
        }
        return result;
    }

    @Override
    public ArrayList<Question> getAllPendingQuestions(int idPersonne, int idCanal)
            throws SQLException {
        Connection connection = daoFactory.getConnection();
        ArrayList<Question> result = new ArrayList();
        // prepare questions selection
        PreparedStatement selectQuestionStmt
                = connection.prepareStatement(
                        SELECT_ALL_PENDING_QUESTIONS_BY_PERSONNE_ID_AND_CANAL_ID);
        selectQuestionStmt.setInt(1, idPersonne);
        selectQuestionStmt.setInt(2, idCanal);
        ResultSet rs = selectQuestionStmt.executeQuery();
        // Pour chaque question trouvée:
        while (rs.next()) {
            // convert int to enum of TypeQuestion
            Question.TypeQuestion type
                    = Question.TypeQuestion.values()[rs.getInt("id_type_question")];
            // Initialiser le bean 
            Question questionFound = new Sondage(
                    rs.getInt("id_question"),
                    type,
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("prenom") + " " + rs.getString("nom"),
                    rs.getString("libelle"));
            assignerPropositions(connection, questionFound);
            assignerReponses(connection, questionFound);
            result.add(questionFound);
        }
        return result;
    }

    @Override
    public void insertReponse(Reponse reponse)
            throws SQLException {
        Connection connection = daoFactory.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(
                INSERT_REPONSE);
        stmt.setLong(1, reponse.getIdQuestion());
        stmt.setLong(2, reponse.getIdPersonne());
        stmt.setString(3, reponse.getLibelle());

        stmt.execute();
    }

    @Override
    public ArrayList<Reponse> getAllReponses()
            throws SQLException {
        Connection connection = daoFactory.getConnection();
        ArrayList<Reponse> result = new ArrayList();
        // prepare questions selection
        PreparedStatement selectQuestionStmt
                = connection.prepareStatement(
                        "SELECT* FROM reponse");
        ResultSet rs = selectQuestionStmt.executeQuery();
        // Pour chaque question trouvée:
        while (rs.next()) {
            // Initialiser le bean 
            Reponse found = new Reponse();
            found.setIdPersonne(rs.getInt("id_personne"));
            found.setIdQuestion(rs.getInt("id_question"));
            found.setLibelle("libelle");
            result.add(found);
        }
        return result;
    }


    @Override
    public ArrayList<Question> findAllByCanal(int idCanal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
