package serveur.metier;

import java.util.ArrayList;

public class Joueur {
	private String pseudo;
	private int credit;
	private int mise;
	private int nbCarte;
	private boolean blackJack;
	private boolean gagnant;
	private int score;
	private int gains;
	private int x;
	private int ID;

	public Joueur(String pseudo, int credit, int x) {
		this.pseudo = pseudo;
		this.credit = credit;
		this.mise = 10;
		this.nbCarte = 0;
		this.blackJack = false;
		this.gagnant = false;
		this.score = 0;
		this.gains = 0;
		this.x = x;
	}
	
	public Joueur(String pseudo, int credit, int x, int ID) {
		this.pseudo = pseudo;
		this.credit = credit;
		this.mise = 10;
		this.nbCarte = 0;
		this.blackJack = false;
		this.gagnant = false;
		this.score = 0;
		this.gains = 0;
		this.x = x;
		this.ID = ID;
	}
	
	public void ajoutCarte(int valeur) {
		nbCarte++;
		score += valeur;
		if(nbCarte == 2 && score == 21)
			this.blackJack = true;
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getCredit() {
		return credit;
	}

	public int getMise() {
		return mise;
	}

	public int getNbCarte() {
		return nbCarte;
	}

	public boolean isBlackJack() {
		return blackJack;
	}

	public void setBlackJack(boolean b) {
		this.blackJack = b;
	}

	public int getScore() {
		return score;
	}

	public boolean isGagnant() {
		return this.gagnant;
	}

	public void setGagnant(boolean b) {
		this.gagnant = b;
	}

	public int getGains() {
		return this.gains;
	}

	public void setGains(int i) {
		this.gains = i;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		int base = 500;
		if(pseudo.equals("Banque")) base = 200;
		int ajout = 30 * nbCarte;
		
		return base - ajout;
	}
	
	public int getID() {
		return this.ID;
	}
}