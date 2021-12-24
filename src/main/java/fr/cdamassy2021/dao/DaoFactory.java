package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Groupe2
 * 23/12/2021
 * Permet de ne pas citer la classe implementation dans le code afin de ne pas devoir changer le code si l'implementation change (invesrsion de dependance),
 * et aussi afin de pouvoir utiliser plus d'une implementation adaptées respectivement à plus d'une persistence.
 *
 */
public class DaoFactory {

	String url, username, password;

	public DaoFactory(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * Crée une instance parametrée de DaoFactory et l'associe au driver.
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
	 * Groupe2 
	 * 23/12/2021
	 * Associe la factory à une connexion à la base de données.
	 */
	public Connection getConnection() {
		Connection con=null;
		try {
			con= DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoFormateur à une factory sans faire appel explicitement à l'implementation du premier.
	 */
	 
	public DaoFormateur getFormateurDao() {
		return new FormateurDao(this);
		
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoEFG à une connection à travers la factory sans faire appel explicitement à l'implementation du premier.
	 */
	public DaoEFG getEfgDao() {
		return new EfgDao(this);
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoCanal à une connection à travers la factory sans faire appel explicitement à l'implementation du premier.
	 */
	public DaoCanal getCanalDao() {
		return new CanalDao(this);
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoGroupeEfg à une connection à travers la factory sans faire appel explicitement à l'implementation du premier.
	 */
	public DaoGroupeEfg getGroupeEfgDao() {
		
		return new GroupeEfgDao(this);
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoQuestionnaire à une connection à travers la factory sans faire appel explicitement à l'implementation du premier.
	 */
	public DaoQuestionnaire getQuestionnaireDao() {
		
		return new QuestionnaireDao(this);
	}
	/**
	 * Groupe2 
	 * 23/12/2021
	 * lie DaoPersonne à une connection à travers la factory sans faire appel explicitement à l'implementation du premier.
	 */
	public DaoPersonne getPersonneDao() {
		return new PersonneDao(this);

	}
	
	
}
