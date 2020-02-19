package serveur.metier;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import serveur.controleur.Controleur;

public class InterpreteurMessages {
	private Controleur ctrl;
	private Metier metier;

	public InterpreteurMessages(Controleur ctrl, Metier metier) {
		this.ctrl = ctrl;
		this.metier = metier;
	}

	public String[] separerMessage(String msg) {
		String[] stringSepare = new String[3];
		int tailleStr = msg.length();
		int cpt = -1;
		int nbPoint = 0;
		int premierPoint = 0;
		char curseur;

		while (nbPoint < 2 || cpt < tailleStr - 1) {
			cpt++;
			curseur = msg.charAt(cpt);
			if (curseur == ':') {
				nbPoint++;
				switch (nbPoint) {
				case 1:
					premierPoint = cpt;
					stringSepare[0] = msg.substring(0, premierPoint);
					break;
				case 2:
					stringSepare[1] = msg.substring(premierPoint + 1, cpt);
					stringSepare[2] = msg.substring(cpt + 1);
					break;
				}
			}
		}

		return stringSepare;
	}

	public int InterpreterMessage(String[] elementsMsg) {
		Joueur tmp;

		switch (elementsMsg[1]) {
		case "connect":
			if (ctrl.verifConnexion(separeLoginMdp(elementsMsg[2])[0], separeLoginMdp(elementsMsg[2])[1]))
				return 1;
		case "litiere":
			if (envoyerMessageLitiere(elementsMsg[0], elementsMsg[2]))
				return 2;
		case "carte":
			if (metier.getJoueurEnCours().getPseudo().equals(elementsMsg[0])) {
				ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(metier.getJoueur(elementsMsg[0])));
			}
			if (metier.getJoueurEnCours().getScore() > 21) {
				metier.passer();
				ctrl.getServer().sendAtAllClients("serveur:joueur:" + metier.getJoueurEnCours().getPseudo());
				if (metier.getJoueurEnCours().getPseudo().equals("Banque"))
					jouerBanque();
				return 6;
			}
			return 3;
		case "create":
			creerJoueur(separeLoginMdp(elementsMsg[2]));
			return 4;
		case "passe":
			if (metier.getJoueurEnCours().getPseudo().equals(elementsMsg[0])) {
				metier.passer();
				ctrl.getServer().sendAtAllClients("serveur:joueur:" + metier.getJoueurEnCours().getPseudo());
				if (metier.getJoueurEnCours().getPseudo().equals("Banque"))
					jouerBanque();
				return 5;
			}
			return 5;
		case "commencer":
			for (Joueur j : metier.getJoueurs()) {
				ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(j));
				ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(j));
			}
			ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(metier.getBanque()));
			ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(metier.getBanque()));
			ctrl.getServer().sendAtAllClients("serveur:started: ");
			return 7;
		default:
			return 0;
		}
	}

	private boolean creerJoueur(String[] tab) {

		if (!ctrl.verifCreation(tab[0]))
			return false;
		if (!ctrl.createUser(tab[0], tab[1]))
			return false;
		return true;
	}

	private String[] separeLoginMdp(String idConnect) {
		String[] loginMdp = new String[2];
		int indSeparateur = idConnect.indexOf("|");

		loginMdp[0] = idConnect.substring(0, indSeparateur);
		loginMdp[1] = idConnect.substring(indSeparateur + 1);

		return loginMdp;
	}

	private boolean envoyerMessageLitiere(String nomJoueur, String message) {
		/*
		 * int indexSalle = -1; int indexJoueur = -1; ArrayList<Salle>
		 * listeSalle = metier.getListeSalle(); for (int i = 0; i <
		 * listeSalle.size(); i++) { ArrayList<Joueur> listeJoueurS =
		 * listeSalle.get(i).getListeJoueurS(); for (int j = 0; j <
		 * listeJoueurS.size(); j++) { if
		 * (listeJoueurS.get(j).getPseudo().equals(nomJoueur)) { indexSalle = i;
		 * indexJoueur = j; break; } } }
		 * 
		 * if (indexSalle != -1 && indexJoueur != -1) { return true; } return
		 * false;
		 */
		return true;
	}

	private void jouerBanque() {
		Joueur banque = metier.getBanque();

		while (banque.getScore() < 17)
			ctrl.getServer().sendAtAllClients(metier.getPile().retirerCarte(metier.getBanque()));

		String resultat = new Resultat(metier.getJoueurs(), banque).getResultat();
		ctrl.getServer()
				.sendAtAllClients("serveur:resultats:" + resultat);
		
		ctrl.distribuerGains(resultat);
	}
	
	
}
