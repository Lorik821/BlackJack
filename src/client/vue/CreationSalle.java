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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreationSalle extends JDialog {
	private CreationSalleInfo infos = new CreationSalleInfo();

	private boolean sendData;
	private JLabel nomLabel, verrouillageLabel;
	private JComboBox verrouillage;
	private JTextField nom;

	public CreationSalle(JFrame parent, String title) {

		super(parent, title, true);

		this.setSize(550, 130);

		this.setLocationRelativeTo(null);

		this.setResizable(false);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.initComponent();

	}

	public CreationSalleInfo showInfo() {

		this.sendData = false;

		this.setVisible(true);

		return this.infos;

	}

	private void initComponent() {

		// Le nom

		JPanel panNom = new JPanel();

		panNom.setBackground(Color.white);

		panNom.setPreferredSize(new Dimension(220, 60));

		nom = new JTextField();

		nom.setPreferredSize(new Dimension(100, 25));

		panNom.setBorder(BorderFactory.createTitledBorder("Nom de la salle"));

		nomLabel = new JLabel("Saisir un nom :");

		panNom.add(nomLabel);

		panNom.add(nom);

		// Le verrouillage

		JPanel panVerrouillage = new JPanel();

		panVerrouillage.setBackground(Color.white);

		panVerrouillage.setPreferredSize(new Dimension(220, 60));

		panVerrouillage.setBorder(BorderFactory.createTitledBorder("Verrouillage de la Salle"));

		verrouillage = new JComboBox();

		verrouillage.addItem("Public");

		verrouillage.addItem("Privée");

		verrouillageLabel = new JLabel("Verouillage : ");

		panVerrouillage.add(verrouillageLabel);

		panVerrouillage.add(verrouillage);

		JPanel content = new JPanel();

		content.setBackground(Color.white);

		content.add(panNom);

		content.add(panVerrouillage);

		JPanel control = new JPanel();

		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				infos = new CreationSalleInfo(nom.getText(), (String) verrouillage.getSelectedItem());

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

}
