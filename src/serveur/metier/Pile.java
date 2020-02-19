package serveur.metier;

import java.util.ArrayList;

import java.util.Collections;

public class Pile {
	private ArrayList<Carte> pile;
	private Metier metier;

	private String[] couleur;
	private String[] num;

	public Pile(Metier metier) {
		this.metier = metier;
		pile = new ArrayList<Carte>();

		couleur = new String[4];
		couleur[0] = "Carreau";
		couleur[1] = "Coeur";
		couleur[2] = "Trefle";
		couleur[3] = "Pique";

		num = new String[13];
		for (int i = 0; i < 13; i++) {
			switch (i) {
			case 0:
				num[i] = "as";
				break;
			case 1:
				num[i] = "deux";
				break;
			case 2:
				num[i] = "trois";
				break;
			case 3:
				num[i] = "quatre";
				break;
			case 4:
				num[i] = "cinq";
				break;
			case 5:
				num[i] = "six";
				break;
			case 6:
				num[i] = "sept";
				break;
			case 7:
				num[i] = "huit";
				break;
			case 8:
				num[i] = "neuf";
				break;
			case 9:
				num[i] = "dix";
				break;
			case 10:
				num[i] = "valet";
				break;
			case 11:
				num[i] = "dame";
				break;
			case 12:
				num[i] = "roi";
				break;
			}
		}

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < couleur.length; j++) {
				if (i < 10)
					pile.add(new Carte(num[i], couleur[j], i + 1));
				else
					pile.add(new Carte(num[i], couleur[j], 10));
			}
		}

		Collections.shuffle(pile);
	}

	public String retirerCarte(Joueur j) {
		String trame = "";

		trame += j.getX() + "x" + j.getY();
		if (pile.get(pile.size() - 1).getValeur() == 1) {
			if ((j.getScore() + 11) < 22)
				j.ajoutCarte(11);
			else
				j.ajoutCarte(1);
		} else
			j.ajoutCarte(pile.get(pile.size() - 1).getValeur());

		trame += ":carte:";
		trame += pile.get(pile.size() - 1).getNom() + "|" + pile.get(pile.size() - 1).getCouleur();
		pile.remove(pile.size() - 1);

		return trame;
	}

	public ArrayList<Carte> getPile() {
		return this.pile;
	}
}
