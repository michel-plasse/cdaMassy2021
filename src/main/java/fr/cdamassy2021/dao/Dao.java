package fr.cdamassy2021.dao;

import java.util.ArrayList;


/**
 * rajout de la methode update
 * Groupe2
 *
 * @param <T>
 */
public interface Dao<T> {
	
	public T findById(long id);
	public ArrayList<T> findAll();
	public  int insert(T t);
	public boolean update(T t);
	public boolean delete(T t);

	

}
