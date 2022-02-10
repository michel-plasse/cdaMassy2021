package fr.cdamassy2021.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author scdel Exercices en groupe
 */
@Entity
public class EFG {
	
	@Id
	private int id;
	private int idCreateur, idCanal;
	private String intitule;
	// private List<Groupe> groupes;

	public EFG() {
	}

	public EFG(int id, int idCreateur, int idCanal, String intitule) {
		this.id = id;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCreateur() {
		return idCreateur;
	}

	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}

	public int getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Override
	public String toString() {
		return "EFG n°" + id + ", crée par " + idCreateur + " dans " + idCanal + ". Intitule: " + intitule;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + this.id;
		hash = 89 * hash + this.idCreateur;
		hash = 89 * hash + this.idCanal;
		hash = 89 * hash + Objects.hashCode(this.intitule);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EFG other = (EFG) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.idCreateur != other.idCreateur) {
			return false;
		}
		if (this.idCanal != other.idCanal) {
			return false;
		}
		if (!Objects.equals(this.intitule, other.intitule)) {
			return false;
		}
		return true;
	}

}
