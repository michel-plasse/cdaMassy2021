package fr.cdamassy2021.dto;

/**
 * Classe intermediaire pour l'affichage du @QUERY dans MbrGrpEFGRepository 
 * @author cmoustache
 *
 */
public class MbrGrpEFGDto {
	
	//from MbrGrpEFG
	private int idPersonne;
	private int idCreateur;// eleve
	private int idEfg;
	
	// from Personne
	private String nom;
	private String prenom;	

	//from EFG
	private String intitule;
	private  int idCanal;
	private int idCreateurCanalExo;
	private String groupes;
	
	//RESPECTER ORDRE DES PARAMETRES
	public MbrGrpEFGDto(int idPersonne, int idCreateur, int idEfg, 
						String nom, String prenom,
						String intitule, int idCanal, int idCreateurCanalExo, String groupes) {
		
		super();
		this.idPersonne = idPersonne;
		this.idCreateur = idCreateur;  //eleve
		this.idEfg = idEfg;
		this.nom = nom;
		this.prenom = prenom;
		this.intitule = intitule;
		this.idCanal = idCanal;
		this.idCreateurCanalExo = idCreateurCanalExo;
		this.groupes = groupes;
		
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

	public String getIntitule() {
		return intitule;
	}
	public int getIdCanal() {
		return idCanal;
	}
	public String getGroupes() {
		return groupes;
	}

	public int getIdCreateurCanalExo() {
		return idCreateurCanalExo;
	}

}
