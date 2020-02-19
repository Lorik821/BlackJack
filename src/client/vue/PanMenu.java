package client.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import client.metier.Musique;

public class PanMenu extends JPanel implements ActionListener {
	private FenetrePrincipale fp;
	private Musique m;
	private Image fond;
	private Dimension dim;

	private JList listeSalles;
	private JButton rejoindreSalle;
	private JButton creerSalle;
	private JButton refresh;
	private JButton retour;
	private JLabel titre;

	private ArrayList<String> listRoom;

	public PanMenu(FenetrePrincipale fp, Musique m, Image fond, ArrayList<String> listRoom) {
		this.fp = fp;
		this.m = m;
		this.fond = fond;
		this.dim = new Dimension(600, 650);
		this.listRoom = listRoom;
		if (listRoom != null)
			this.listeSalles = new JList(listRoom.toArray());
		else
			this.listeSalles = new JList();

		// Création des elements
		this.listeSalles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.rejoindreSalle = new JButton("Rejoindre");
		this.rejoindreSalle.addActionListener(this);
		this.creerSalle = new JButton("Creer");
		this.creerSalle.addActionListener(this);
		this.refresh = new JButton("Rafraichir");
		this.refresh.addActionListener(this);
		this.retour = new JButton("Quitter");
		this.retour.addActionListener(this);
		this.titre = new JLabel("Liste des salles");
		this.titre.setFont(new Font("Papyrus", Font.PLAIN, 14));

		// Création du panel
		this.setLayout(null);
		this.add(titre);
		this.titre.setBounds(90, 100, 380, 20);
		this.add(listeSalles);
		this.listeSalles.setBounds(90, 125, 380, 400);
		this.add(rejoindreSalle);
		this.rejoindreSalle.setBounds(480, 135, 110, 20);
		this.add(creerSalle);
		this.creerSalle.setBounds(480, 165, 110, 20);
		this.add(refresh);
		this.refresh.setBounds(480, 195, 110, 20);
		this.add(retour);
		this.retour.setBounds(480, 225, 110, 20);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(fond, 0, 0, null);
	}

	public Dimension getDimension() {
		return dim;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rejoindreSalle) {
			if (listeSalles.getSelectedValue() != null) {
				String selected = listRoom.get(listeSalles.getSelectedIndex());
				boolean conn = true;
				if (isLocked(selected)) {
					conn = false;
					// on verifie le mdp
					String proposition = (String) JOptionPane.showInputDialog(fp, "Entrez le mot de passe :",
							"Salle verouillée", JOptionPane.PLAIN_MESSAGE, null, null, "");
					if (proposition.equals(getPass(selected))) {
						conn = true;
					} else {
						JOptionPane.showMessageDialog(fp, "Mot de passe éroné", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (conn) {
					connect(selected);
				}
			} else {
				JOptionPane.showMessageDialog(fp, "Vous devez sélectionner une salle pour pouvoir la rejoindre",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}

		}
		if (e.getSource() == creerSalle) {
			CreationSalle cs = new CreationSalle(null, "Création d'une salle");

			CreationSalleInfo infos = cs.showInfo();

			JOptionPane jop = new JOptionPane();

			jop.showMessageDialog(null, infos.toString(), "Information création de la salle",
					JOptionPane.INFORMATION_MESSAGE);

			if (infos.isCreated()) {
				String nomSalle = infos.getNom();
				String verrouillage = infos.getVerrouillage();

				// on appel le serveur pour qu'il puis creer la salle
				refreshList();
			}
		}
		if (e.getSource() == refresh) {
			refreshList();
		}
		if (e.getSource() == retour) {
			fp.setPanel(fp.getPanelConnexion(), fp.getPanelConnexion().getDimension());
		}
	}

	public boolean isLocked(String name) {
		// on demande au serveur si la salle est verrouillee

		return false;
	}

	public String getPass(String name) {
		// on demande au serveur le mdp de la salle

		return "";
	}

	public void connect(String name) {
		// on se connecte a la salle
	}

	public void refreshList() {
		listeSalles = new JList(getNames().toArray());
	}

	public ArrayList<String> getNames() {
		// on demande au serveur la liste des noms

		return new ArrayList<String>();
	}
}
