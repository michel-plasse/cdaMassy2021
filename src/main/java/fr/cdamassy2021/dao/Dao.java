/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thoma
 * 
 * @T = Bean Type
 */

public interface Dao<T>{ // method CRUD: Create/Read/Update/Delete
	
	public boolean insert (T inserted) throws SQLException;
	
	public void delete(T deleted) throws SQLException;
	
	public T findById(long id) throws SQLException;
	
	public ArrayList<T> findAll() throws SQLException;
        
        public ArrayList<T> getAllPaging(int noPage, int nbElementsParPage) throws SQLException;
}
