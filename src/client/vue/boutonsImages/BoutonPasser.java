package client.vue.boutonsImages;

import java.awt.event.MouseEvent;

import client.metier.Musique;
import client.vue.PanJeux;

public class BoutonPasser extends BoutonImage {

	private PanJeux panJeux;
	public BoutonPasser(String boutonSimple, String boutonCliquable, Musique m, PanJeux panJeux) {
		super(boutonSimple, boutonCliquable, m);
		this.panJeux = panJeux;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		panJeux.passer();
	}

}
