package fr.cdamassy2021.model;

import java.sql.Date;
import java.util.ArrayList;

import fr.cdamassy2021.dao.DaoCanal;
import fr.cdamassy2021.dao.DaoEFG;
import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.DaoFormateur;
import fr.cdamassy2021.dao.DaoGroupeEfg;

/**
 * @author Groupe2 23/12/2021
 */
public class EFG {

	private int idEFG;
	private int idCreateur;
	private int idCanal;
	private String intitule;
	private Date dateCreation;
	private int tailleMinGroupe;


	/**
	 * 
	 */
	public EFG() {
		super();

	}

	/**
	 * @param idEFG
	 * @param idCreateur
	 * @param idCanal
	 * @param intitule
	 * @param dateCreation
	 * @param tailleMinGroupe
	 */
	public EFG(int idEFG, int idCreateur, int idCanal, String intitule, Date dateCreation, int tailleMinGroupe) {
		super();
		this.idEFG = idEFG;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
		this.dateCreation = dateCreation;
		this.tailleMinGroupe = tailleMinGroupe;
	}

	/**
	 * @param idCreateur
	 * @param idCanal
	 * @param intitule
	 * @param dateCreation
	 * @param tailleMinGroupe
	 */
	public EFG(int idCreateur, int idCanal, String intitule, Date dateCreation, int tailleMinGroupe) {
		super();
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
		this.dateCreation = dateCreation;
		this.tailleMinGroupe = tailleMinGroupe;
	}

	/**
	 * @param idCreateur
	 * @param idCanal
	 * @param intitule
	 * @param dateCreation
	 */
	public EFG(int idCreateur, int idCanal, String intitule, Date dateCreation) {
		super();
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the idEFG
	 */
	public int getIdEFG() {
		return idEFG;
	}

	/**
	 * @param idEFG the idEFG to set
	 */
	public void setIdEFG(int idEFG) {
		this.idEFG = idEFG;
	}

	/**
	 * @return the idCreateur
	 */
	public int getIdCreateur() {
		return idCreateur;
	}

	/**
	 * @param idCreateur the idCreateur to set
	 */
	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}

	/**
	 * @return the idCanal
	 */
	public int getIdCanal() {
		return idCanal;
	}

	/**
	 * @param id_canal the id_canal to set
	 */
	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Groupe2 23/12/2021
	 * 
	 * REGLES DE CREATION ET DE REMPLISSAGE DES GROUPES D'UN EXERCICE EFG.
	 * 
	 * On fixe un nombre minium (tailleMinGroupe) d'eleves dans un groupe. Une
	 * colonne taille_minimale_groupe (necessairement non nulle) sera ajoutée à la
	 * table efg.
	 * 
	 * On obtient le nombre de groupe_efgs souhaité comme resultat de la division
	 * eucludienne du nombre de membres du canal par tailleMinGroupe :
	 * nbGroupes=(int)tailleCanal/tailleMinGroupe. Un groupe peut être créé tant que
	 * nbGroupes n'est pas atteint.
	 * 
	 * La taille maximale d'un groupe sera déduite des calculs précédents:
	 * tailleMaxGroupe=(int) (tailleCanal/nbGroupes)+1
	 * 
	 * 
	 * La fixation de tailleMinGroupe conduit de fait à un état final où nbGroupes
	 * est necessairement atteint.
	 * 
	 * l'eleve s'inscrit au groupe de son choix tant que la tailleMaxGroupe n'y est
	 * pas atteinte.
	 */

	public int getTailleMaxGroupe() {
		DaoCanal daoCanal = DaoFactory.getInstance().getCanalDao();
		int tailleCanal = daoCanal.getAllMembresByIdCanal(idCanal).size();
		int nbGroupes = getNbGroupes();
		return (int) (tailleCanal / nbGroupes) + 1;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Utilise la Dao pour obtenir le nombre des membre dans le canal afin
	 *         de calculer le nombre de groupes à partir du nombre de membres du
	 *         canal et de la taille minimale des groupes de l'exercice EFG.
	 */
	public int getNbGroupes() {

		DaoCanal daoCanal = DaoFactory.getInstance().getCanalDao();
		return daoCanal.getAllMembresByIdCanal(idCanal).size() / tailleMinGroupe;
	}

	/**
	 * @return the tailleMinGroupe
	 */
	public int getTailleMinGroupe() {
		return tailleMinGroupe;
	}

	/**
	 * @param tailleMinGroupe the tailleMinGroupe to set
	 */
	public void setTailleMinGroupe(int tailleMinGroupe) {
		this.tailleMinGroupe = tailleMinGroupe;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Utilise la Dao pour retourner la liste de ses groupes à un efg de
	 *         EFG.
	 */
	public ArrayList<GroupeEfg> getGroupes() {

		DaoEFG daoEfg = DaoFactory.getInstance().getEfgDao();
		ArrayList<GroupeEfg> grpes = daoEfg.getAllGroupesByIdEfg(idEFG);
		return grpes;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Crée un groupe d'un exercice EFG Crée une liste de membres qu'elle
	 *         associe au groupe crée ci-dessus en y mettant un premier élément, son
	 *         créateur. Utilise la Dao pour inserer le groupe créé.
	 */
	public Boolean creerGroupe(int idCreateur) {

		GroupeEfg groupe = new GroupeEfg();
		DaoGroupeEfg daoGroupeEfg = DaoFactory.getInstance().getGroupeEfgDao();
		boolean bool = false;

		if (daoGroupeEfg.findGroupeByIdEfgIdMembre(idEFG, idCreateur) != null) {
			groupe.setIdEfg(idEFG);
			groupe.setIdCreateur(idCreateur);
			ArrayList<Personne> liste = new ArrayList<Personne>();
			Personne personne = new Personne();
			personne.setId(idCreateur);
			liste.add(personne);
			groupe.setMembres(liste);
			daoGroupeEfg.insert(groupe);
			bool = true;
		}
		return bool;
	}

	@Override
	public String toString() {
		return "EFG [idEFG=" + idEFG + ", idCreateur=" + idCreateur + ", idCanal=" + idCanal + ", intitule=" + intitule
				+ ", dateCreation=" + dateCreation + ", tailleMinGroupe=" + tailleMinGroupe + "]";
	}

	/**
	 * @author Groupe2 23/12/2021
	 */

}
