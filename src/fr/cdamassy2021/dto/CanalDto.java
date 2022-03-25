package fr.cdamassy2021.dto;

import fr.cdamassy2021.entity.Canal;

public class CanalDto {
	private long idCanal;
	private String nom;
	
	public long getIdCanal() {
		return idCanal;
	}
	public void setIdCanal(long idCanal) {
		this.idCanal = idCanal;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public CanalDto() {
	}
	
	public CanalDto(Canal canal) {
		this.idCanal = canal.getIdCanal();
		this.nom = canal.getNom();
	}
	
	
	

}
