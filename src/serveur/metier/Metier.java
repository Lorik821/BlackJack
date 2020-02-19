package serveur.metier;

import java.util.ArrayList;

import client.metier.reseau.Client;

public class Metier {
	public static final int CREDIT = 200;
	public static final int PORT = 5600;
	public static final String SERVEUR = "di-722-09";

	private Client client;
	private Pile pile;
	private Joueur joueurEnCours;
	private Joueur banque;
	private int cptJoueurSuivant;

	private ArrayList<Joueur> listeJoueur;
	private ArrayList listeSalle;

	public Metier() {
		listeJoueur = new ArrayList<Joueur>();
		listeSalle = new ArrayList<Salle>();
		listeSalle.add(new Salle());
		pile = new Pile(this);
		banque = new Joueur("Banque", 6000, 500);
		joueurEnCours = null;
		cptJoueurSuivant = 0;
	}

	public void addUser(String pseudo, int credit, int ID) {
		int x = listeJoueur.size() * 300 + 100;
		Joueur j = new Joueur(pseudo, credit, x, ID);
		if (x == 100)
			joueurEnCours = j;
		listeJoueur.add(j);
	}
	
	public void retirerJoueur(int index) {
		listeJoueur.remove(index);
	}

	public ArrayList<Salle> getListeSalle() {
		return listeSalle;
	}

	public Pile getPile() {
		return this.pile;
	}

	public Joueur getJoueur(String pseudo) {
		for(Joueur j : listeJoueur) 
			if(j.getPseudo().equals(pseudo))
				return j;
		return null;
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return this.listeJoueur;
	}

	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}

	public void setJoueurEnCours(Joueur j) {
		this.joueurEnCours = j;
	}

	public void passer() {
		cptJoueurSuivant++;
		if (cptJoueurSuivant < listeJoueur.size()) {
			while(listeJoueur.get(cptJoueurSuivant)==null) {
				cptJoueurSuivant++;
				if(listeJoueur.size() <= cptJoueurSuivant) break;
			}
			if(cptJoueurSuivant < listeJoueur.size())	
				joueurEnCours = listeJoueur.get(cptJoueurSuivant);
			else
				joueurEnCours = this.banque;
		}
		else
			joueurEnCours = this.banque;
	}

	public Joueur getBanque() {
		return this.banque;
	}
}
