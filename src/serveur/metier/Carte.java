package serveur.metier;

public class Carte {
	private String 	nom;
	private String 	couleur;
	private int 	valeur;
	
	public Carte( String nom, String couleur, int valeur) {
		this.nom 		= nom;
		this.couleur 	= couleur;
		this.valeur 	= valeur;
	}

	public String getNom() {
		return nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public int getValeur() {
		return valeur;
	}
}
