package client.vue.boutonsImages;

import java.awt.event.MouseEvent;

import client.metier.Musique;
import client.vue.PanConnexion;
import client.vue.PanJeux;

public class BoutonCommencer extends BoutonImage {

	private PanJeux panJeux;
	public BoutonCommencer(String boutonSimple, String boutonCliquable, Musique m, PanJeux panJeux) {
		super(boutonSimple, boutonCliquable, m);
		this.panJeux = panJeux;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		panJeux.commencer();
		this.setVisible(false);
	}

}
