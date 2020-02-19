package serveur.controleur;

import java.util.ArrayList;
import java.util.Scanner;

import serveur.metier.InterpreteurMessages;
import serveur.metier.Metier;
import serveur.metier.Sgbd;
import serveur.metier.serveur.Server;

public class Controleur {

	private Sgbd sgbd;
	private Metier metier;
	private InterpreteurMessages im;
	private Server server;
	public Controleur(Metier metier, Sgbd sgbd) {
		this.metier = metier;
		im = new InterpreteurMessages (this, metier);
		this.sgbd = sgbd;
		server = null;
		server = new Server (Metier.PORT, im, metier, this);
	}
	
	public boolean verifConnexion (String login, String mdp)
	{
		
		ArrayList<String> listLogin = sgbd.getLogins();
		for (String item : listLogin)
		{
			if (item.equals(login)) {
				if (sgbd.getPass(login).equals(mdp))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
	
	public boolean verifCreation (String login) {
		ArrayList<String> listLogin = sgbd.getLogins();
		for (String item : listLogin)
		{
			if (item.equals(login)) 
				return false;
			else 
				return true;
		}
		return false;
	}
	
	public boolean createUser(String log, String mdp) {
		return (sgbd.insertNewUser(log, mdp, Metier.CREDIT));
	}
	
	public int getCredit(String log) {
		return sgbd.getCredits(log);
	}
	
	public Server getServer() {
		return this.server;
	}
	
	public Sgbd getSgbd() {
		return this.sgbd;
	}
	
	public void distribuerGains(String resultats) {
		Scanner sc = new Scanner(resultats);
		sc.useDelimiter(" ");
		while(sc.hasNext()){
			String pseudo = sc.next();
			int gains = sc.nextInt();
			int credit = sgbd.getCredits(pseudo);
			sgbd.updateCredit(pseudo, credit+gains);
		}
	}
}
