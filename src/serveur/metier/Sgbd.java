package serveur.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sgbd {
	
	public Connection conn;
	
	public Sgbd() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "lj140542", "lj140542");
		/*Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from personne");*/
	}
	
	public void closeConnection() {
		try {
			this.conn.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	/* -------------------------------------------- */
	/* 				G�re les Utilisateurs			*/
	/* -------------------------------------------- */
	
	
	public ArrayList<String> getLogins() {
		ArrayList<String> retour = new ArrayList<String>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from utilisateur");
			while (rset.next()) {
				retour.add(rset.getString(1));
			}
		}
		catch(SQLException e) { System.out.println("Erreur lors de la recuperation des logins"); }
		
		return retour;
	}
	
	public String getPass(String login) {
		String retour = "";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from utilisateur where login = '" + login + "'");
			while (rset.next()) {
				retour = rset.getString(2);
			}
		}
		catch(SQLException e) { System.out.println("Erreur lors de la recuperation du mdp de " + login); }
		
		return retour;
	}
	
	public int getCredits(String login) {
		int retour = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from utilisateur where login = '" + login + "'");
			while (rset.next()) {
				retour = Integer.parseInt(rset.getString(3));
			}
		}
		catch(SQLException e) { System.out.println("Erreur lors de la recuperation des credits de " + login); }
		
		
		return retour;
	}
	
	public boolean insertNewUser(String login, String pass, int credits) { 
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("insert into utilisateur values('" + login + "','" + pass + "'," + credits +")");
			return true;
		}
		catch(SQLException e) { return false;}
	}
	
	public void updateCredit(String login, int credits) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("update utilisateur set credit = " + credits + " where login = '" + login + "'");
		}
		catch(SQLException e) { System.out.println("Erreur lors de la maj des credit de " + login);}
	}
	
	public void updatePass(String login, String pass) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("update utilisateur set pass = '" + pass + "' where login = '" + login + "'");
		}
		catch(SQLException e) { System.out.println("Erreur lors de la maj du mdp de " + login);}
	}
	
	public void deleteUser(String login) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("delete utilisateur where login = '" + login + "'");
		}
		catch(SQLException e) { System.out.println("Erreur lors de la suppression de " + login);}
	}
}
