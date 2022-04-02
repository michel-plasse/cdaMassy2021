package fr.cdamassy2021.pkclasses;

import java.io.Serializable;
import java.util.Objects;

public class GrpEFGPK2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCreateur;
	private int idEfg;
	
	public GrpEFGPK2() {}

	public GrpEFGPK2(int idCreateur, int idEfg) {
		super();
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCreateur, idEfg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrpEFGPK2 other = (GrpEFGPK2) obj;
		return idCreateur == other.idCreateur && idEfg == other.idEfg;
	}

	
}
