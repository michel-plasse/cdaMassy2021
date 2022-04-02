package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import fr.cdamassy2021.pkclasses.GrpEFGPK2;

@Entity
@IdClass(GrpEFGPK2.class)
@Table(name="groupe_efg")
public class GrpEFG {
	@Id
	@Column(name="id_createur")
	private int idCreateur;
	@Id
	@Column(name="id_efg")
	private int idEfg;
		
	public GrpEFG() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GrpEFG(int idCreateur, int idEfg) {
		super();
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
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
