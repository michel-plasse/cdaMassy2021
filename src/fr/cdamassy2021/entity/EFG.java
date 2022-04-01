package fr.cdamassy2021.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name="id_efg")
	private int idEfg;

	// Une fois que l'id Cr�ateur de canal est ajout� � la BDD, on peut ajouter la gestoin de la cl�
	//�trang�re (@Many-to-one ). Il faut que le cr�ateur de l'EFG soit le cr�ateur du canal.
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="id_createur",referencedColumnName = "id_personne"),
		@JoinColumn(name="id_canal",referencedColumnName = "id_canal")
	})
	private MembreCanal createur;

	@Column
	private String intitule;
	@Column
	private String groupes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "efg")
	private List<MbrGrpEFG> mbrGrpEFGs;

	public EFG() {
		this.createur= new MembreCanal();
	}

	public EFG(String intitule, String groupes) {
		this.intitule = intitule;
		this.groupes = groupes;
		this.createur= new MembreCanal();
		
	}

	public EFG(int idEfg, int idCreateur, int idCanal, String intitule, String groupes) {
		this.idEfg = idEfg;
		this.createur= new MembreCanal();
		this.createur.setIdCanal(idCanal);
		this.createur.setIdPersonne(idCreateur);
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
		return getCreateur().getIdPersonne();
	}

	public void setIdCreateur(int idCreateur) {
		this.createur.setIdPersonne(idCreateur);
	}

	public int getIdCanal() {
		return getCreateur().getIdCanal();
	}

	public void setIdCanal(int idCanal) {
		this.createur.setIdCanal(idCanal);
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
	public MembreCanal getCreateur() {
		return createur;
	}

	public void setCreateur(MembreCanal createur) {
		this.createur = createur;
	}
	@Override
	public String toString() {
		return "EFG n°" + idEfg + ", crée par " + getIdCreateur() + " dans " + getIdCanal() + ". Intitule: " + intitule;
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
