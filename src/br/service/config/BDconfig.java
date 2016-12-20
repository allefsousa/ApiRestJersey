package br.service.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconfig {
	/*
	 * classe de conexao com a base de dados
	 */
	public static  Connection getconnection()throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/notes_db","root","");
	}
}
