package client.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.metier.reseau.Client;

public class Litiere extends JPanel implements ActionListener
{
	private JPanel panelSud;
	//private JTextArea zoneSaisie;
	private JTextField zoneSaisie;
	private JTextArea historique;
	private JScrollPane sp;
	private JButton envoyer;
	private Client client;
	private JScrollBar jsb;
	
	private String pseudo;
	
	public Litiere (Client client, String pseudo)
	{
		this.pseudo = pseudo;
		this.client = client;
		//setTitle ("litière");
		setLayout(new BorderLayout ());
	
		panelSud = new JPanel (new BorderLayout());
		
		//zoneSaisie = new JTextArea (3, 20);
		zoneSaisie = new JTextField (20);
		zoneSaisie.addActionListener(this);
		panelSud.add(zoneSaisie);
		
		envoyer = new JButton ("envoyer");
		envoyer.addActionListener(this);
		panelSud.add(envoyer, BorderLayout.EAST);
		
		historique = new JTextArea (10, 20);
		historique.setEditable(false);
		historique.setBackground(new Color (255, 255, 153));
		sp = new JScrollPane (historique);
		
		jsb = sp.getVerticalScrollBar();
		
		add (sp);
		add (panelSud, BorderLayout.SOUTH);
		
		
		//pack();
		//setLocationRelativeTo(null);
		setVisible (true);
	}
	
	public void setPseudo (String str) { this.pseudo = str; }
	public String getPseudo () { return this.pseudo; }
	
	public void addText (String pseudo, String text)
	{
		
		jsb.setValue(jsb.getMaximum());
		System.out.println(jsb.getMinimum() + "\n" + jsb.getValue() + "\n" + jsb.getMaximum());
		historique.setText(historique.getText() + "\n" + pseudo + " : " + text);
        revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == envoyer || arg0.getSource() == zoneSaisie)
		{
			String msg = zoneSaisie.getText();
			client.setMessageLitiere(msg);
			zoneSaisie.setText("");
		}
		
	}
}
