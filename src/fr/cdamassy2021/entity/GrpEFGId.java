package fr.cdamassy2021.entity;

import java.io.Serializable;
import java.util.Objects;

public class GrpEFGId implements Serializable{
	
	private int idCreateur;
	private int idCanal;
	
	public GrpEFGId() {}

	public GrpEFGId(int idCreateur, int idCanal) {
		super();
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCanal, idCreateur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrpEFGId other = (GrpEFGId) obj;
		return idCanal == other.idCanal && idCreateur == other.idCreateur;
	};
	
}
