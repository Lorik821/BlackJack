package client.vue;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import client.metier.Metier;

public class InterpreteurMessages 
{
	private Litiere litiere;
	private FenetrePrincipale fp;
	private Metier metier;
	
	public InterpreteurMessages (FenetrePrincipale fp)
	{
		this.fp = fp;

	}

	public InterpreteurMessages (Litiere litiere, Metier metier, FenetrePrincipale fp)
	{
		this.litiere = litiere;
		this.metier = metier;
		this.fp = fp;
	}
	
	public String[] separerMessage (String msg)
	{
		String[] stringSepare = new String[3];
		int tailleStr = msg.length();
		int cpt = -1;
		int nbPoint = 0;
		int premierPoint = 0;
		char curseur;
		
		while (nbPoint < 2 || cpt < tailleStr-1)
		{
			cpt++;
			curseur = msg.charAt(cpt);
			if (curseur == ':')
			{
				nbPoint++;
				switch (nbPoint)
				{
					case 1: premierPoint = cpt;
							stringSepare[0] = msg.substring(0, premierPoint);
							break;
					case 2: stringSepare[1] = msg.substring(premierPoint+1, cpt);
							stringSepare[2] = msg.substring(cpt+1);
							break;
				}				
			}
		}
		
		return stringSepare;
	}
	
	public void InterpreterMessage (String[] elementsMsg)
	{
		
		switch (elementsMsg[1])
		{
			case "litiere"    : litiere.addText(elementsMsg[0], elementsMsg[2]);
							   break;			
			case "connect"    : connectionFailed (elementsMsg[2]); 
							   break;		
			case "carte"      : receptionCarte (elementsMsg[0], elementsMsg[2]); 
							   break;
			case "joueur"     : setJoueurEnCours (elementsMsg[2]);
							   break;
			case "commencer"  : hotePartie();
							   break;
			case "started"    : started();
							   break;
			case "resultats"  : trierResultat (elementsMsg[2]); 
							  break;
			case "credits"    : afficherCredit(elementsMsg[2]); 
							  break;
		}
	}
	
	private void afficherCredit (String credit)
	{		
		fp.getPanelJeux().setCredit("Crédit : " + credit);
	}
	
	private void trierResultat (String res)
	{
		String resultat = "";
		boolean b = false;
		Scanner sc = new Scanner(res);
		sc.useDelimiter(" ");
		while (sc.hasNext())
		{
			String pseudo = sc.next();
			String score = sc.next();
			try {
			if (pseudo.equals(metier.getClient().getPseudo()) && Integer.parseInt(score) > 0)
					b = true;
			} catch (Exception e) { b = false; }	
			
			resultat += "Gain(s) de " + pseudo + " : " + score + "\n"; 
		}
		
		fp.getPanelJeux().afficherRes(resultat, b);
	}
	
	private void started ()
	{
		fp.getPanelJeux().enabledLabel();
		fp.setTitle("BlackJack - " + metier.getClient().getPseudo());
	}
	
	private void hotePartie ()
	{
		fp.getPanelJeux().ajouterBoutonCommencer();
	}
	
	private void setJoueurEnCours (String nomJoueur)
	{
		metier.setClientEnCours(nomJoueur);
		fp.getPanelJeux().setMessageLabel (metier.getNomJoueurEnCours());
	}
	
	public void receptionCarte (String coord, String msg)
	{
		int x = 0;
		int y = 0;
		String carte = "";
		carte += msg.substring(0, msg.indexOf("|"));
		carte += msg.substring(msg.indexOf("|")+1);
		carte += ".png";
		
		x = Integer.parseInt(coord.substring(0, coord.indexOf("x")));
		y = Integer.parseInt(coord.substring(coord.indexOf("x")+1));
		
		fp.getPanelJeux().afficherNouvelleCarte (carte, x, y);
	}
	
	public void connectionFailed (String message)
	{
		if (message.equals("failed")) 
			JOptionPane.showMessageDialog(fp, "L'identifiant ou le mot de passe est incorrect");
		else {
			fp.setPanel(fp.getPanelJeux(), fp.getPanelJeux().getDimension());
		}
	}
	
	private String[] separeLoginMdp(String idConnect)
	{
		String[] loginMdp = new String[2];
		int indSeparateur = idConnect.indexOf("|");
		
		loginMdp[0] = idConnect.substring(0, indSeparateur);
		loginMdp[1] = idConnect.substring(indSeparateur+1);
		
		return loginMdp; 
	}
}

