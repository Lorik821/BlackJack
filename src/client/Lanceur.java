package client;

import client.controleur.Controleur;
import client.metier.Metier;
import client.metier.Musique;
import client.vue.FenetrePrincipale;
import client.vue.Litiere;

public class Lanceur {

	public static void main(String[] args) {
		
		Musique m = new Musique ();
		Metier metier = new Metier();
		Controleur controleur = new Controleur (metier);
		new FenetrePrincipale(m, metier, controleur);
	}

}
