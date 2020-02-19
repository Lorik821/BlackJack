package client.vue;

public class CreationJoueurInfo {

	private String nom, pass;
	private boolean created;

	public CreationJoueurInfo() {
	}

	public CreationJoueurInfo(String nom, String pass) {

		this.nom = nom;

		this.pass = pass;
	}

	public String toString() {

		String str;

		if (this.nom != null && this.pass != null) {

			str = "Le joueur à été créé avec les valeurs suivantes :\n\n";

			str += "Nom : " + this.nom + "\n";

			str += "Mot de Passe : Ce que vous avez saisie\n";
			this.created = true;
		}

		else {

			str = "Le joueur n'a pas été créé";
			this.created = false;
		}

		return str;

	}

	public String getNom() {
		return this.nom;
	}

	public String getPass() {
		return this.pass;
	}

	public boolean isCreated() {
		return this.created;
	}
}
