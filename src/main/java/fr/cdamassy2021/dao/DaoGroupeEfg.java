package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.GroupeEfg;

public interface DaoGroupeEfg extends Dao<GroupeEfg> {
	
	public GroupeEfg findById(long idEfg, long idCreateur);
}
