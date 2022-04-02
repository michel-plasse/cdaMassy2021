package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.cdamassy2021.pkclasses.MbrGrpEFGPK3;

@Entity
@IdClass(MbrGrpEFGPK3.class)
@Table(name = "membre_groupe_efg")
public class MbrGrpEFG {
	
	@Id
	@Column(name="id_personne")
	private int idPersonne;
	@Id
	@Column(name="id_createur")
	private int idCreateur;
	@Id
	@Column(name="id_efg")
	private int idEfg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_personne", referencedColumnName = "id_personne", insertable = false, updatable = false)
	@JsonIgnore
	Personne personne;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_efg", referencedColumnName = "id_efg", insertable = false, updatable = false)
	@JsonIgnore
	EFG efg;

	public MbrGrpEFG() { }
	
	public MbrGrpEFG(int idPersonne, int idCreateur, int idEfg) {
		this.idPersonne = idPersonne;
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
	}

	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public int getIdCreateur() {
		return idCreateur;
	}
	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}
	public int getIdEfg() {
		return idEfg;
	}
		public void setIdEfg(int idEfg) {
		this.idEfg = idEfg;
	}
	

}
