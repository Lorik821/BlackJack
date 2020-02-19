package client.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.metier.Metier;
import client.metier.Musique;
import client.vue.boutonsImages.BoutonCommencer;
import client.vue.boutonsImages.BoutonImage;
import client.vue.boutonsImages.BoutonNouvelleCarte;
import client.vue.boutonsImages.BoutonPasser;

public class PanJeux extends JPanel
{
	private FenetrePrincipale fp;
	private Metier metier;
	private Musique m;
	private Image fond;
	private Dimension dim;
	
	private BoutonImage nouvelleCarte;
	private BoutonImage passer;
	private BoutonImage commencer;
	
	private JLabel nomJoueurActuel;
	private JLabel credits;
	
	private ArrayList<JLabel> listeImageCarte;
	
	public PanJeux (FenetrePrincipale fp, Musique m, Image fond, Metier metier)
	{
		this.fp = fp;
		this.m = m;
		this.fond = fond;
		this.metier = metier;
		this.nouvelleCarte = new BoutonNouvelleCarte ("BoutonNouvelleCarte.png", "BoutonNouvelleCarteHover.png", m, this);
		this.passer = new BoutonPasser ("BoutonPasser.png", "BoutonPasserHover.png", m, this);
		nomJoueurActuel = new JLabel ("");
		nomJoueurActuel.setFont (new Font ("Papyrus", Font.PLAIN, 30));
		credits = new JLabel("");
		credits.setFont (new Font ("Papyrus", Font.PLAIN, 20));
		credits.setForeground(Color.WHITE);
		add (credits);
		credits.setBounds(30, 885, 1280, 30);
		add(nomJoueurActuel);
		JLabel banque = new JLabel (new ImageIcon (FenetrePrincipale.CHEMIN + "/ressources/images/cartes/dosCarte.png"));
		add(banque);
		banque.setBounds(600, 200, 90, 150);
		
		/*
		 * Il Faut gérer l'emplacement du nom des joueurs à partir du serveur, le serveur doit signaler quel joueur est actif
		 */
		dim = new Dimension (1280, 960);
		listeImageCarte = new ArrayList<JLabel>();
		setLayout (null);
		add (nouvelleCarte);
		add (passer);
		
		repaint();
		revalidate();
	}
	
	public void setMessageLabel (String msg)
	{
		nomJoueurActuel.setText(msg);
		nomJoueurActuel.setBounds(500, 50, 1280, 30);
		System.out.println("Le message est : " + msg);
		repaint();
		revalidate();
	}
	
	public void setCredit (String credit)
	{
		credits.setText(credit);
		repaint();
		revalidate();
	}
	
	public void paintComponent (Graphics g) {
		g.drawImage(fond, 0, 0, null);
	}
	
	public Dimension getDimension () { return dim; }
	
	public void afficherNouvelleCarte (String nom, int x, int y)
	{
		Image i = new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/cartes/" + nom).getImage().getScaledInstance(90, 150, Image.SCALE_DEFAULT);
		listeImageCarte.add(new JLabel (new ImageIcon (i)));
		add(listeImageCarte.get(listeImageCarte.size()-1));
		listeImageCarte.get(listeImageCarte.size()-1).setBounds(x, y, 90, 150);
		m.playBruitDeCarte();
		repaint();
		revalidate();
	}
	
	public void passer () { metier.envoyerMsgPasser(); }
	
	
	public void nouvelleCarte () 
	{ 
		metier.envoyerMsgCarte();
		try { Thread.sleep(50); }
		catch (InterruptedException e) {}
		
	}


	public void ajouterBoutonCommencer() {
		commencer = new BoutonCommencer ("BoutonCommencer.png", "BoutonCommencerHover.png", m, this);
		add (commencer);
		commencer.setBounds(453, 800, 393, 76);
	}


	public void commencer() {
		metier.envoyerMsgCommencer();		
	}


	public void enabledLabel() {
		nouvelleCarte.setBounds(30, 800, 393, 76);
		passer.setBounds(850, 800, 393, 76);
	}


	public void afficherRes(String resultat, boolean musique) {
		JOptionPane.showMessageDialog(this, resultat);
		if (musique)
			m.playFanfare();
	}
}
