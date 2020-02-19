package client.vue.boutonsImages;

import java.awt.event.MouseEvent;

import client.metier.Musique;
import client.vue.PanConnexion;

public class BoutonImageCreerUnCompte extends BoutonImage {

	private PanConnexion panConnexion;
	public BoutonImageCreerUnCompte(String boutonSimple, String boutonCliquable, Musique m, PanConnexion panConnexion) {
		super(boutonSimple, boutonCliquable, m);
		this.panConnexion = panConnexion;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		panConnexion.creerUnCompte();
	}

}
