package client.vue.boutonsImages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.metier.Musique;
import client.vue.FenetrePrincipale;

public abstract class BoutonImage extends JLabel implements ActionListener, MouseListener 
{
	protected Musique m;
	protected String boutonSimple;
	protected String boutonCliquable;
	
	public BoutonImage (String boutonSimple, String boutonCliquable, Musique m)
	{
		this.m = m;
		this.boutonSimple = boutonSimple;
		this.boutonCliquable = boutonCliquable;
		setIcon (new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/" + boutonSimple));
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
			setIcon (new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/" + boutonCliquable));
			repaint();
			m.playPetitBruit();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
			setIcon (new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/" + boutonSimple));
			repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
