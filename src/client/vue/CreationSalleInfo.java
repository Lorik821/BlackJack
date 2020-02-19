package client.vue;

public class CreationSalleInfo {

	private String nom, verrouillage;
	private boolean created;

	public CreationSalleInfo() {
	}

	public CreationSalleInfo(String nom, String verrouillage) {

		this.nom = nom;

		this.verrouillage = verrouillage;
	}

	public String toString() {

		String str;

		if (this.nom != null && this.verrouillage != null) {

			str = "La salle à été créée avec les valeurs suivantes :\n\n";

			str += "Nom : " + this.nom + "\n";

			str += "Verrouillage : " + this.verrouillage + "\n";
			this.created = true;
		}

		else {

			str = "La salle n'a pas été créée";
			this.created = false;
		}

		return str;

	}

	public String getNom() {
		return this.nom;
	}

	public String getVerrouillage() {
		return this.verrouillage;
	}

	public boolean isCreated() {
		return this.created;
	}
}
