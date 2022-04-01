package fr.cdamassy2021.dto;
/**
 * Classe intermediaire pour l'affichage du @QUERY dans MbrGrpEFGRepository 
 * @author cmoustache
 *
 */
public class MbrGrpEFGDto {
	
	//from MbrGrpEFG
	private int idPersonne;
	private int idCreateur;
	private int idEfg;
	
// from Personne
	private String nom;
	private String prenom;
//	private String prenomCreateur;
//	private String nomCreateur;
	
// 	from EFG
//	private String groupes;
//	private MembreCanal createur; pour idCanal à analyser ?????
	
	public MbrGrpEFGDto(int idPersonne, int idCreateur, int idEfg, String nom, String prenom) {
		super();
		this.idPersonne = idPersonne;
		this.idCreateur = idCreateur;
		this.idEfg = idEfg;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getIdPersonne() {
		return idPersonne;
	}
	
	public int getIdCreateur() {
		return idCreateur;
	}
	public int getIdEfg() {
		return idEfg;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}

	

}
