package client.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Options extends JDialog implements ActionListener
{
	private JButton ok;
	private JButton annuler;
	private JButton appliquer;
	
	private JPanel panelSud;
	
	
	public Options ()
	{
		setTitle ("Options");
		setModal (true);
		setSize (350, 200);
		setLocationRelativeTo(null);
		setLayout (new BorderLayout());
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		
		OngletSon ongletSon = new OngletSon();
		
		onglets.addTab("Son", ongletSon);
		
		onglets.setOpaque(true);
		add (onglets);
		
		panelSud = new JPanel (new FlowLayout(FlowLayout.RIGHT));
		
		ok = new JButton ("OK");
		ok.addActionListener(this);
		panelSud.add(ok);
		
		annuler = new JButton ("Annuler");
		annuler.addActionListener(this);
		panelSud.add(annuler);
		
		appliquer = new JButton ("Appliquer");
		appliquer.addActionListener(this);
		panelSud.add(appliquer);
		
		add (panelSud, BorderLayout.SOUTH);
		
		setVisible (true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == annuler)
			this.dispose();
		if (e.getSource() == ok)
		{
			
		}
		if (e.getSource() == appliquer)
		{
			
		}
	}
}
