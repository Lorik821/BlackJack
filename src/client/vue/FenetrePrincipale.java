package client.vue;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import client.controleur.Controleur;
import client.metier.Metier;
import client.metier.Musique;


public class FenetrePrincipale extends JFrame implements ActionListener {
	private Controleur controleur;
	private Metier metier;
	private Musique m;

	public static final String CHEMIN = System.getProperty("user.dir");
	private JPanel panelConnexion;
	private JPanel panelJeux;
	private JPanel panelMenu;

	private JMenuBar menuBar;

	private JMenu fileMenu;
	private JMenuItem quitter;

	private JMenu configMenu;
	private JMenuItem son;

	public FenetrePrincipale(Musique m, Metier metier, Controleur controleur) {
		this.metier = metier;
		this.controleur = controleur;
		this.m = m;
		setTitle("BlackJack");

		//menuBar = new JMenuBar();

		// le menu Fichier
		/*fileMenu = new JMenu("Fichier");
		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(this);
		fileMenu.add(quitter);*/

		// le menu Configuration
		/*configMenu = new JMenu("Configuration");
		son = new JMenuItem("Son");
		son.addActionListener(this);
		configMenu.add(son);*/

		// Ajout des menus � la menubar
		//menuBar.add(fileMenu);
//		menuBar.add(configMenu);

		// Ajout de la menubar � la fen�tre
		//setJMenuBar(menuBar);

		m.playMusiqueAmbiance();
		m.playBruitAmbiance();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
            //I skipped unused callbacks for readability

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					metier.envoyerMsgDeconection();
				} catch (Exception ex) { System.exit(0); }
				System.exit(0);
				
			}
		});
		 
		setIconImage(new ImageIcon(CHEMIN + "/ressources/images/IconeFenetre.png").getImage());
		panelConnexion = new PanConnexion(this, m, new ImageIcon(CHEMIN + "/ressources/images/Fond.png").getImage(),
				controleur, metier, "BoutonQuitter.png", "BoutonQuitterHover.png", "BoutonCreerUnCompte.png",
				"BoutonCreerUnCompteHover.png", "BoutonOK.png", "BoutonOKHover.png");
		panelJeux = new PanJeux(this, m,
				new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/tapisBlackJack.jpg").getImage(), metier);
		panelMenu = new PanMenu(this, m,
				new ImageIcon(FenetrePrincipale.CHEMIN + "/ressources/images/Fond.png").getImage(), null);
		setPanel(panelConnexion, ((PanConnexion) panelConnexion).getDimension());
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setPanel(JPanel panel, Dimension dim) {
		setResizable (true);
		setContentPane(panel);
		setSize(dim);
		setLocationRelativeTo(null);
		repaint();
		revalidate();
		repaint();
		setResizable (false);
	}

	public PanJeux getPanelJeux() {
		return (PanJeux) panelJeux;
	}

	public PanMenu getPanelMenu() {
		return (PanMenu) panelMenu;
	}

	public PanConnexion getPanelConnexion() {
		return (PanConnexion) panelConnexion;
	}
	
	public Musique getMusique () { return m; }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == quitter)
			System.exit(0);
		if (arg0.getSource() == son)
			new Options();

	}
}
