package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

import serveur.controleur.Controleur;
import serveur.metier.InterpreteurMessages;
import serveur.metier.Metier;
import serveur.metier.Sgbd;
import serveur.metier.serveur.Server;

public class Lanceur {

	public static void main(String[] args) 
	{
		Metier metier = new Metier ();
		Sgbd sgbd = null;
		try {
			sgbd = new Sgbd();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Controleur controleur = new Controleur (metier, sgbd);
	}

}
