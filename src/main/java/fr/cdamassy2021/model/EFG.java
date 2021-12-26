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

	int idEFG;
	int idCreateur;
	int idCanal;
	String intitule;
	Date dateCreation;
	int tailleMinGroupe;

	ArrayList<GroupeEfg> groupes;

	public EFG(int idEFG, int idCreateur, int idCanal, String intitule, Date dateCreation) {
		super();
		this.idEFG = idEFG;
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
		this.dateCreation = dateCreation;
	}

	public EFG() {
		super();

	}

	public EFG(int idCreateur, int idCanal, String intitule) {
		super();
		this.idCreateur = idCreateur;
		this.idCanal = idCanal;
		this.intitule = intitule;
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
	 * @author Groupe2 23/12/2021
	 * 
	 *         On fixe un nombre minium (tailleMinGroupe) d'eleves dans un groupe.
	 *         on obtient le nombre de groupe_efgs comme resultat de la division
	 *         eucludienne du nombre de membres du canal par tailleMinGroupe.
	 *         tailleMaxGroupe=tailleMinGroupe + partie entiere de
	 *         (tailleCanal/nombre de groupes)+1 l'eleve s'inscrit au groupe de son
	 *         choix tant que la tailleMaxGroupe n'est pas atteinte. le nombre de
	 *         groupes sera alors nombre de membres du canal/min. le reste de la
	 *         division sera reparti sur les groupes tel qu'aucun groupe ne depasse
	 *         la tailleMaximale qu'on calcal comme suit: Quand un eleve essaie de
	 *         s'inscrire à un groupe d'un exercice efg il faudra la precondition
	 *         efg.tailleGroupe<efg.taillemaxgroupe (à calculer comme indiqué)
	 */

	public int getTailleMaxGroupe() {
		DaoCanal daoCanal = DaoFactory.getInstance().getCanalDao();
		int tailleCanal = daoCanal.getAllMembresByIdCanal(idCanal).size();
		int nbGroupes = getNbGroupes();
		return tailleMinGroupe + (int) (tailleCanal / nbGroupes) + 1;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Utilise la Dao pour obtenir le nombre de groupes déjà créés de
	 *         l'exercice EFG.
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
	 * @return the groupes
	 */
	public ArrayList<GroupeEfg> getGroupes() {
		return groupes;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 * @param groupes the groupes to set Utilise la Dao pour attribuer la liste de
	 *                ses groupes à un efg de EFG.
	 */
	public void setGroupes(ArrayList<GroupeEfg> groupes) {
		DaoEFG daoEfg = DaoFactory.getInstance().getEfgDao();
		this.groupes = daoEfg.getAllGroupesByIdEfg(idEFG);
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Crée un groupe d'un exercice EFG Crée une liste de membres qu'elle
	 *         associe au groupe crée ci-dessus en y mettant un premier élément, son
	 *         créateur. Utilise la Dao pour inserer le groupe créé.
	 */
	public GroupeEfg creerGroupe(int idCreateur) {

		GroupeEfg groupe = new GroupeEfg();
		groupe.setIdEfg(idEFG);
		groupe.setIdCreateur(idCreateur);
		ArrayList<Personne> liste = new ArrayList<Personne>();
		Personne personne = new Personne();
		personne.setId(idCreateur);
		liste.add(personne);
		groupe.setMembres(liste);
		DaoGroupeEfg daoGroupeEfg = DaoFactory.getInstance().getGroupeEfgDao();
		daoGroupeEfg.insert(groupe);
		return groupe;
	}

	/**
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public String toString() {
		return "EFG [idEFG=" + idEFG + ", idCreateur=" + idCreateur + ", idCanal=" + idCanal + ", intitule=" + intitule
				+ ", dateCreation=" + dateCreation + ", tailleMinGroupe=" + tailleMinGroupe + ", groupes=" + "]";
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + this.id;
		hash = 89 * hash + this.idCreateur;
		hash = 89 * hash + this.idCanal;
		hash = 89 * hash + Objects.hashCode(this.intitule);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EFG other = (EFG) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.idCreateur != other.idCreateur) {
			return false;
		}
		if (this.idCanal != other.idCanal) {
			return false;
		}
		if (!Objects.equals(this.intitule, other.intitule)) {
			return false;
		}
		return true;
	}

}
