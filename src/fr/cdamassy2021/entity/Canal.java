package fr.cdamassy2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
*
* @author borel,beatrice,huawei
*/
@Entity
public class Canal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCanal;
	@Column
	private String nom;
	
	public Canal() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor 
	 * 
	 * @param idCanal
	 * @param nom
	 */
	public Canal(Long idCanal, String nomCanal) {
		// TODO Auto-generated constructor stub
		this.idCanal= idCanal;
		this.nom= nomCanal;
		
	}
	
	public Long getIdCanal() {
		return idCanal;
	}


	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}


	public String getNomCanal() {
		return nom;
	}


	public void setNomCanal(String nomCanal) {
		this.nom = nomCanal;
	}
}
