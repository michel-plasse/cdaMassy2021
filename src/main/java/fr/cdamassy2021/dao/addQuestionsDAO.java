package fr.cdamassy2021.dao;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.cdamassy2021.model.addQuestions;

public class addQuestionsDAO {
	 public int cdamassy2021(addQuestions addquestions) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO addquestions" +
	            "  (libelleQuestion, libelleProposition, undefined, correct, incorrect, intituleQuestion) VALUES " +
	            " (?, ?, ?, ?,?,?);";

	        int result = 0;

	        Class.forName("com.mysql.jdbc.Driver");

	        //Step 1: Established the connection with database
	      try (Connection connection = DriverManager
	     .getConnection("jdbc:mysql://localhost/cdamassy2021?noAccessToProcedureBodies=true");

	        // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement =  connection.prepareStatement(INSERT_USERS_SQL)) {                
	    	    preparedStatement.setString(1,addquestions.getlibelleQuestion());
	            preparedStatement.setString(2,addquestions.getlibelleProposition());
	            preparedStatement.setString(3,addquestions.getundefined());
	            preparedStatement.setString(4,addquestions.getcorrect());
	            preparedStatement.setString(5,addquestions.getincorrect());
	            preparedStatement.setString(6,addquestions.getintituleQuestion());

	        // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	}
