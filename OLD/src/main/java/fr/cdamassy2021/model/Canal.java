package fr.cdamassy2021.model;

/**
*
* @author borel,beatrice,huawei
*/

public class Canal {
	private int idCanal;
	private String nomCanal;
	
	public Canal() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructeur 
	 * 
	 * @param idCanal
	 * @param nomCanal
	 */
	public Canal(int idCanal, String nomCanal) {
		// TODO Auto-generated constructor stub
		this.idCanal= idCanal;
		this.nomCanal= nomCanal;
		
	}
	
	public int getIdCanal() {
		return idCanal;
	}


	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}


	public String getNomCanal() {
		return nomCanal;
	}


	public void setNomCanal(String nomCanal) {
		this.nomCanal = nomCanal;
	}
}
