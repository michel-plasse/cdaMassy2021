
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.Question;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thoma
 *
 * @T = Bean Type
 */
public interface IDao<T> { // method CRUD: Create/Read/Update/Delete

    public int insert(T inserted) throws SQLException;

    public void delete(T deleted) throws SQLException;

    public T findById(long id) throws SQLException;

    public ArrayList<T> findAll() throws SQLException;

    public ArrayList<T> findAllByCanal(int idCanal) throws SQLException;

    public ArrayList<T> getAllPaging(int noPage, int nbElementsParPage) throws
        SQLException;
}
