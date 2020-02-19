package client.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.controleur.Controleur;
import client.metier.Metier;
import client.metier.Musique;
import client.vue.boutonsImages.BoutonImage;
import client.vue.boutonsImages.BoutonImageCreerUnCompte;
import client.vue.boutonsImages.BoutonImageOk;
import client.vue.boutonsImages.BoutonImageQuitter;

public class PanConnexion extends JPanel implements ActionListener, MouseListener {
	private Controleur controleur;
	private Metier metier;
	private FenetrePrincipale fp;
	private Musique m;
	private Image fond;

	private JTextField login;
	private JPasswordField password;

	private Dimension dim;

	private BoutonImage quitter;
	private BoutonImage creerUnCompte;
	private BoutonImage ok;
	
	private InterpreteurMessages interpreteurConnexion;

	public PanConnexion(FenetrePrincipale fp, Musique m, Image fond, Controleur controleur, Metier metier,
			String... args) {
		this.controleur = controleur;
		this.metier = metier;
		this.fp = fp;
		this.m = m;
		this.fond = fond;
		this.quitter = new BoutonImageQuitter(args[0], args[1], m, this);
		this.creerUnCompte = new BoutonImageCreerUnCompte(args[2], args[3], m, this);
		this.ok = new BoutonImageOk(args[4], args[5], m, this);

		dim = new Dimension(600, 635);
		setLayout(null);
		Color tf = new Color(255, 255, 153);
		login = new JTextField(20);
		login.addActionListener(this);
		login.setBackground(tf);
		password = new JPasswordField(20);
		password.addActionListener(this);
		password.setBackground(tf);

		JLabel textLogin = new JLabel("Login");
		textLogin.setFont(new Font("Papyrus", Font.PLAIN, 14));
		JLabel textPassword = new JLabel("Mot de passe");
		textPassword.setFont(new Font("Papyrus", Font.PLAIN, 14));

		add(textLogin);
		add(textPassword);
		add(login);
		add(password);
		add(quitter);
		add(creerUnCompte);
		add(ok);

		textLogin.setBounds(200, 30, 200, 40);
		login.setBounds(200, 60, 200, 30);
		textPassword.setBounds(200, 90, 200, 40);
		password.setBounds(200, 120, 200, 30);
		quitter.setBounds(100, 500, 395, 76);
		creerUnCompte.setBounds(100, 368, 395, 76);
		ok.setBounds(100, 227, 395, 76);

		addMouseListener(this);

	}

	public void paintComponent(Graphics g) {
		g.drawImage(fond, 0, 0, null);
	}

	public Dimension getDimension() {
		return dim;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ok();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void ok() {
		// JOptionPane.showMessageDialog(null, "Le programme n'est pas
		// ternmin�", "Failure", JOptionPane.ERROR_MESSAGE );
		// fp.setPanel(fp.getPanelJeux(), fp.getPanelJeux().getDimension());
		// fp.setPanel(fp.getPanelMenu(), fp.getPanelMenu().getDimension());

		String log = login.getText();
		String mdp = new String(password.getPassword());
		if (controleur.checkId(log, mdp)) {
			interpreteurConnexion = new InterpreteurMessages (fp);
			metier.newClient(fp);
			metier.envoyerMessageAuServeur(log, "connect", log + "|" + mdp);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Erreur dans l'insertion du login ou du mot de passe");
		}
		//this.initCarte();
	}
	
	private void initCarte ()
	{
		Thread appelCarte = new Thread() {
			public void run () {
				try { Thread.sleep(500); }
				catch (InterruptedException e) {}
				metier.envoyerMsgCarte();
				try { Thread.sleep(500); }
				catch (InterruptedException e) {}
				metier.envoyerMsgCarte();
			}
		};
		appelCarte.start();
		
	}

	public void creerUnCompte() {
		CreationJoueur cj = new CreationJoueur(null, "Création d'un joueur");

		CreationJoueurInfo infos = cj.showInfo();

		JOptionPane jop = new JOptionPane();

		jop.showMessageDialog(null, infos.toString(), "Information création du joueur",
				JOptionPane.INFORMATION_MESSAGE);

		if (infos.isCreated()) {
			String log = infos.getNom();
			String mdp = infos.getPass();
			
			if (controleur.checkId(log, mdp))
			{
				metier.newClient(fp);
				metier.envoyerMessageAuServeur(log, "create", log + "|" + mdp);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Erreur dans l'insertion du login ou du mot de passe");
			}
		}
	}

	public void quitter() {
		System.exit(0);
	}

	public void ajouterBoutonCommencer() {
		
		
	}
}
