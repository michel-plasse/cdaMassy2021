package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupe_efg")
public class GrpEFG {
	
	private int idCreateur;
	@Id
	@Column(name="id_efg")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEfg;
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
