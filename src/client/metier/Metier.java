package client.metier;

import java.util.ArrayList;

import client.metier.reseau.Client;
import client.vue.FenetrePrincipale;
import client.vue.InterpreteurMessages;
import serveur.metier.Joueur;
import serveur.metier.Salle;

public class Metier 
{
	public static final int PORT = 5600;
	public static final String SERVEUR = "localhost";
	
	private Client client;
	
	private String nomJoueurEnCours;
	
	private ArrayList listeJoueur;
	private ArrayList listeSalle;
	
	public Metier ()
	{
		listeJoueur = new ArrayList<Joueur>();
		listeSalle = new ArrayList<Salle>();
	}
	
	public void newClient (FenetrePrincipale fp)
	{
		client = new Client (SERVEUR, PORT, this, fp);
	}
	
	public void envoyerMessageAuServeur (String login, String type, String msg)
	{
		client.setLM(login+":"+type+":"+msg, login);
	}
	
	public void envoyerMsgCarte  () { client.setMessageCarte  (" "); }
	public void envoyerMsgPasser () { client.setMessagePasser (" "); }
	
	public Client getClient() { return client; }
	public String getNomJoueurEnCours () {
		if (nomJoueurEnCours.equals(client.getPseudo()))
		{
			return "C'est votre tour !";
		}
		else
			return "C'est le tour de " + nomJoueurEnCours;
	}

	public void envoyerMsgCommencer() {
		client.setMessageCommencer();
		
	}

	public void envoyerMsgDeconection() {
		client.setMessageDeconection();
	}
	
	public void setClientEnCours (String client) {
		this.nomJoueurEnCours = client;
	}

}
