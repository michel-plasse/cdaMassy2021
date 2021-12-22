/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

/**
 *
 * @author thoma
 */
public abstract class QuestionDao implements IQuestionDao {

    protected static final String INSERT_QUESTION
            = "INSERT INTO question ("
            + "id_canal,id_createur,libelle,id_type_question) "
            + "VALUES ( ?, ?, ?, ?);";

    protected static final String SELECT_BY_QUESTION_ID
            = "SELECT  q.*, p.nom, p.prenom\n"
            + "FROM question q\n"
            + "	INNER JOIN \n"
            + "		personne p \n"
            + "			ON p.id_personne = q.id_createur\n"
            + "WHERE id_question = ?;";

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
}
