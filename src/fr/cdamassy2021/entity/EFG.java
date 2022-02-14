package fr.cdamassy2021.entity;

import java.util.HashSet;
import java.util.Objects;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author scdel Exercices en groupe
 */
@Entity
public class EFG {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEfg;

	// Une fois que l'id Créateur de canal est ajouté à la BDD, on peut ajouter la gestoin de la clé
	//étrangère (@Many-to-one ). Il faut que le créateur de l'EFG soit le créateur du canal.
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_createur",referencedColumnName = "id_personne"),
		@JoinColumn(name="id_canal",referencedColumnName = "id_canal")
	})
	private MembreCanal créateur;

	@Column
	private String intitule;
	@Column
	private String groupes;

	public EFG() {
		this.créateur= new MembreCanal();
		this.créateur.setEfgCrées(new HashSet<EFG>());
		this.créateur.getEfgCrées().add(this);
	}

	public EFG(String intitule, String groupes) {
		this.intitule = intitule;
		this.groupes = groupes;
		this.créateur.setEfgCrées(new HashSet<EFG>());
		this.créateur.getEfgCrées().add(this);
	}

	public EFG(int idEfg, int idCreateur, int idCanal, String intitule, String groupes) {
		this.idEfg = idEfg;
		this.créateur.setIdCanal(idCanal);
		this.créateur.setIdPersonne(idCreateur);
		this.créateur.setEfgCrées(new HashSet<EFG>());
		this.créateur.getEfgCrées().add(this);
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
		return getCréateur().getIdPersonne();
	}

	public void setIdCreateur(int idCreateur) {
		this.créateur.setIdPersonne(idCreateur);
	}

	public int getIdCanal() {
		return getCréateur().getIdCanal();
	}

	public void setIdCanal(int idCanal) {
		this.créateur.setIdCanal(idCanal);
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
	public MembreCanal getCréateur() {
		return créateur;
	}

	public void setCréateur(MembreCanal créateur) {
		this.créateur = créateur;
	}
	@Override
	public String toString() {
		return "EFG nÂ°" + idEfg + ", crÃ©e par " + getIdCreateur() + " dans " + getIdCanal() + ". Intitule: " + intitule;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + this.idEfg;
		hash = 89 * hash + this.getIdCanal();
		hash = 89 * hash + this.getIdCreateur();
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

		if (!Objects.equals(this.intitule, other.intitule)) {
			return false;
		}
		return true;
	}

}
