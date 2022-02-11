package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(MembreCanalId.class)
public class MembreCanal {
	@Id
	@Column(name="id_canal")
	private int idCanal;
	
	@Id
	@Column(name="id_personne")
	private int idPersonne;

}
