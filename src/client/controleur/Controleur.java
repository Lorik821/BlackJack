package client.controleur;

import client.metier.Metier;

public class Controleur 
{
	private Metier metier;
	public Controleur (Metier metier)
	{
		this.metier = metier;
	}
	
	public boolean checkId (String id, String mdp)
	{
		if (id.contains("|") || id.contains(":") || mdp.contains("|") || mdp.contains(":") || id.equals("") || id == null || mdp.equals("") || mdp == null || id.contains(" "))
			return false;
		return true;
	}
}
