package fr.cdamassy2021.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.cdamassy2021.model.GroupeEfg;
import fr.cdamassy2021.model.Personne;
/**
 * Groupe2
 * 23/12/2021
 */
public interface DaoGroupeEfg extends Dao<GroupeEfg> {
	
	public GroupeEfg findById(long idEfg, long idCreateur);
	public ArrayList<Personne> getAllMembresByIdGroupe(long idEfg, long idCreateur);
	/**
	 * 
	 * @param idMembre
	 * @return le groupe dont est membre la personne dont le id est idMembre, s'il existe.
	 * Va servir à interdir à une personne de créer un groupe si elle est déjà membre d'un groupe.
	 */
	public GroupeEfg findGroupeByIdEfgIdMembre(int idEfg,int idMembre) ;
}
