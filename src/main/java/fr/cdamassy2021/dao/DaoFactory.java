package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	String url, username, password;

	public DaoFactory(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

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
	
	public Connection getConnection() {
		Connection con=null;
		try {
			con= DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public FormateurDao getFormateurDao() {
		return new FormateurDao(this);
		
	}

	public DaoEFG getEfgDao() {
		return new EfgDao(this);
	}

	public DaoCanal getCanalDao() {
		return new CanalDao(this);
	}

	public DaoGroupeEfg getGroupeEfgDao() {
		
		return new GroupeEfgDao(this);
	}
	
	
}
