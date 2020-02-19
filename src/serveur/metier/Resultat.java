package serveur.metier;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Resultat {

	private ArrayList<Joueur> joueurs;
	private Joueur banque;
	private ArrayList<Integer> gains;
	private int nbJoueur;

	public Resultat(ArrayList<Joueur> joueurs, Joueur banque) {
		// initialisation
		if (joueurs != null) {
			this.joueurs = joueurs;
			this.nbJoueur = joueurs.size();
			this.banque = banque;
		}

		// Elimination des perdants et des egalites en cas de victoire de la
		// banque
		boolean ttlemondegagne = false;
		for (int cpt = 0; cpt < nbJoueur; cpt++) {
			Joueur j = joueurs.get(cpt);
			if (j != null) {
				if (banque.getScore() > 21 && j.getScore() <= 21) {
					j.setGagnant(true);
				} else {
					if (j.getScore() <= 21) {
						if (banque.getScore() == 21) {
							if (banque.isBlackJack()) {
								if (j.isBlackJack())
									j.setGains(j.getMise());
								else
									j.setGains(0);
							} else {
								if (j.isBlackJack())
									j.setGagnant(true);
								else {
									if (j.getScore() == 21)
										j.setGains(j.getMise());
									else
										j.setGains(0);
								}
							}
						} else {
							if (banque.getScore() > j.getScore())
								j.setGains(0);
							else {
								if (banque.getScore() < j.getScore()) {
									j.setGagnant(true);
								} else
									j.setGains(j.getMise());
							}

						}
					} else
						j.setGains(0);
				}
			}
		}

		// comptage des gagnants
		int cptGagnant = 0;
		for (Joueur j : joueurs) {
			if (j.isGagnant())
				cptGagnant++;
		}

		// partage des gains pour les gagnants
		if (cptGagnant != 0) {
			for (Joueur j : joueurs) {
				if (j.isGagnant()) {
					if (banque.getScore() <= 21)
						j.setGains(((j.getMise() * nbJoueur) / cptGagnant));
					else
						j.setGains(j.getMise());
				}
			}
		}
	}
	
	public String getResultat() {
		String retour = "";
		for (Joueur j : joueurs) {
			retour += j.getPseudo() + " " + j.getGains() + " ";
		}
		
		retour = retour.substring(0, retour.length()-1);
		
		return retour;
	}
}