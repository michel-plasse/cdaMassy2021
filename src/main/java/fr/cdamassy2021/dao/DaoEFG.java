/**
 * Groupe2
 * 23/12/2021
 */
package fr.cdamassy2021.dao;

import java.util.ArrayList;

/**
 * Groupe2
 * 23/12/2021
 */

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.GroupeEfg;

public interface DaoEFG extends Dao<EFG> {
	
/**
 * get All Groupes By IdEfg
 */
public ArrayList<GroupeEfg> getAllGroupesByIdEfg(int idEfg);

}
