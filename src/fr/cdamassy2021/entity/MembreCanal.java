package fr.cdamassy2021.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(MembreCanalId.class)
public class MembreCanal {
	@Id
	private int idCanal;
	
	@Id
	private int idPersonne;

}
