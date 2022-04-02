package fr.cdamassy2021.pkclasses;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

public class MbrGrpEFGPK3 implements Serializable {
	
	private int idPersonne;
	private int idCreateur;
	private int idEfg;
	
	public MbrGrpEFGPK3() {};

	public MbrGrpEFGPK3(int idPersonne, int idCreateur, int idEfg) {
		this.idPersonne = idPersonne;
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCreateur, idEfg, idPersonne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MbrGrpEFGPK3 other = (MbrGrpEFGPK3) obj;
		return idCreateur == other.idCreateur && idEfg == other.idEfg && idPersonne == other.idPersonne;
	}

	

}
