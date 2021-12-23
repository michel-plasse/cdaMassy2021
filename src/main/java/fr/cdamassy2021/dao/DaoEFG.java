/**
 * 
 */
package fr.cdamassy2021.dao;

import java.util.ArrayList;

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.GroupeEfg;

public interface DaoEFG extends Dao<EFG> {
	
public ArrayList<GroupeEfg> listerGroupesByIdEfg(int idEfg);
public GroupeEfg creerGroupe(int idCreateur);

}
