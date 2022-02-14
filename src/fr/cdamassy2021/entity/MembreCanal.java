package fr.cdamassy2021.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
@IdClass(MembreCanalId.class)
public class MembreCanal {
	@Id
	@Column(name="id_canal")
	private int idCanal;
	
	@Id
	@Column(name="id_personne")
	private int idPersonne;
	
	@OneToMany(mappedBy = "createur",targetEntity = EFG.class)
	private Set<EFG> efgCrees;


	public MembreCanal() {
		super();
	}

	public int getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Set<EFG> getEfgCrees() {
		return efgCrees;
	}

	public void setEfgCrees(Set<EFG> efgCrées) {
		this.efgCrees = efgCrées;
	}
	
	

}
