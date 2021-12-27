package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thoma
 *
 */
public class DaoFactory {

    String url, username, password;

    private DaoFactory(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     *
     *
     */
    public static DaoFactory getInstance() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String url = "jdbc:mysql://localhost:3306/cdamassy2021";
        String username = "root";
        String password = "";
        return new DaoFactory(url, username, password);
    }

    /**
     *
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public QuestionDao getQuestionDao() {
        return new QuestionDaoImpl(this);
    }
}
