package fr.cdamassy2021.entity;

import java.io.Serializable;
import java.util.Objects;

public class MembreCanalId implements Serializable {
	private int idCanal;
	private int idPersonne;

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

	@Override
	public int hashCode() {
		return Objects.hash(idCanal, idPersonne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembreCanalId other = (MembreCanalId) obj;
		return idCanal == other.idCanal && idPersonne == other.idPersonne;
	}

}
