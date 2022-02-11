package fr.cdamassy2021.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author scdel Exercices en groupe
 */
@Entity
public class EFG {

	@Id
	private int idEfg;

	@Column
	private int idCreateur;
	@Column
	private int idCanal;
	@Column
	private String intitule;
	@Column
	private String groupes;

	public EFG() {
	}

	public EFG(String intitule, String groupes) {
		this.intitule = intitule;
		this.groupes = groupes;
	}

	public EFG(int idEfg, int idCreateur, int idCanal, String intitule, String groupes) {
		this.idEfg = idEfg;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
		this.groupes = groupes;
	}

	public int getIdEfg() {
		return idEfg;
	}

	public void setIdEfg(int idEfg) {
		this.idEfg = idEfg;
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

	public String getGroupes() {
		return groupes;
	}

	public void setGroupes(String groupes) {
		this.groupes = groupes;
	}

	@Override
	public String toString() {
		return "EFG n°" + idEfg + ", crée par " + idCreateur + " dans " + idCanal + ". Intitule: " + intitule;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + this.idEfg;
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
		if (this.idEfg != other.idEfg) {
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
