/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;
import java.util.ArrayList;

/**
 *
 * @author thoma
 * 
 * @T = Bean Type
 */

public interface Dao<T>{ // method CRUD: Create/Read/Update/Delete
	
	public boolean insert(T inserted);
	
	public void delete(T deleted);
	
	public T findById(long id);
	
	public ArrayList<T> findAll();
}
