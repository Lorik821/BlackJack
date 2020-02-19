package client.metier;

public class Carte {
	private String 	nom;
	private String 	couleur;
	private int 	valeur;
	private String 	image;
	
	private static String chemin = System.getProperty("user.dir");
	
	public Carte( String nom, String couleur, int valeur, String image) {
		this.nom 		= nom;
		this.couleur 	= couleur;
		this.valeur 	= valeur;
		this.image 		= chemin + "/ressources/images/" + image + ".png";
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

	public String getImage() {
		return image;
	}
}
