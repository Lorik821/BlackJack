package serveur.metier;

import java.util.ArrayList;

public class Salle 
{
	private ArrayList listeJoueurS;
	private Litiere litiere;
	
	private String nom;
	private String motDePasse;
	private boolean verrouille; 
	
	/*
	 * Constructeurs de Salle
	 */
	public Salle ()
	{
		listeJoueurS = new ArrayList<Joueur>();
		litiere = new Litiere();
	}
	
	public Salle (Joueur...tabJoueurs)
	{
		listeJoueurS = new ArrayList<Joueur>(); 
		for (Joueur j:tabJoueurs)
			listeJoueurS.add (j);
	}
	
	public ArrayList<Joueur> getListeJoueurS () { return listeJoueurS; }
	
	/*
	 * Permet d'ajouter un ou plusieurs joueurs � la liste d'une salle
	 */
	public void addJoueur (Joueur...tabJoueurs)
	{
		for (Joueur j:tabJoueurs)
			listeJoueurS.add (j);
	}
	
	public void delJoueur (int indice)
	{
		listeJoueurS.remove(indice);
	}

	public String getName() 
	{
		return this.nom;
	}	
	
	public boolean isLocked() {
		return this.verrouille;
	}

	public String getPass() {
		return this.motDePasse;
	}
}
