package client.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class OngletSon extends JPanel implements ActionListener
{
	private JCheckBox cbSonAmbiance;
	private JCheckBox cbSonBruit;
	private JCheckBox cbSonCarte;
	
	public OngletSon ()
	{
		setPreferredSize(new Dimension(320, 180));
		setLayout(new GridLayout (3, 1));
		
		cbSonAmbiance = new JCheckBox ("Activer la musique d'ambiance", true);
		add (cbSonAmbiance);
		cbSonBruit = new JCheckBox ("Activer le bruit d'ambiance", true);
		add (cbSonBruit);
		cbSonCarte = new JCheckBox ("Activer le bruit des cartes", true);
		add (cbSonCarte);		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
