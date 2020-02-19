package client.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreationJoueur extends JDialog implements ActionListener {
	private CreationJoueurInfo infos = new CreationJoueurInfo();

	private boolean sendData;
	private JLabel nomLabel, passLabel;
	private JTextField nom;
	private JPasswordField pass;

	public CreationJoueur(JFrame parent, String title) {

		super(parent, title, true);

		this.setSize(550, 170);

		this.setLocationRelativeTo(null);

		this.setResizable(false);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.initComponent();

	}

	public CreationJoueurInfo showInfo() {

		this.sendData = false;

		this.setVisible(true);

		return this.infos;

	}

	private void initComponent() {

		// Le nom

		JPanel panNom = new JPanel();

		panNom.setBackground(Color.white);

		panNom.setPreferredSize(new Dimension(220, 80));

		nom = new JTextField();
		
		nom.addActionListener(this);

		nom.setPreferredSize(new Dimension(100, 25));

		panNom.setBorder(BorderFactory.createTitledBorder("Nom du joueur"));

		nomLabel = new JLabel("Saisir un nom :");

		panNom.add(nomLabel);

		panNom.add(nom);

		// Le verrouillage

		JPanel panPass = new JPanel();

		panPass.setBackground(Color.white);

		panPass.setPreferredSize(new Dimension(220, 80));
		
		pass = new JPasswordField();
		
		pass.addActionListener(this);

		pass.setPreferredSize(new Dimension(100, 25));

		panPass.setBorder(BorderFactory.createTitledBorder("Mdp du joueur"));

		passLabel = new JLabel("Saisir un mot de passe :");

		panPass.add(passLabel);

		panPass.add(pass);

		JPanel content = new JPanel();

		content.setBackground(Color.white);

		content.add(panNom);

		content.add(panPass);

		JPanel control = new JPanel();

		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				infos = new CreationJoueurInfo(nom.getText(), pass.getText());

				setVisible(false);
			}

		});

		JButton cancelBouton = new JButton("Annuler");

		cancelBouton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);

			}

		});

		control.add(okBouton);

		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);

		this.getContentPane().add(control, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		infos = new CreationJoueurInfo(nom.getText(), pass.getText());

		setVisible(false);
	}

}
