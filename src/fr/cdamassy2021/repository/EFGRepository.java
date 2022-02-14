package fr.cdamassy2021.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cdamassy2021.entity.EFG;

/**
 * 
 * @author TeamVerte
 * Interface permettant l'interaction avec la base de donnee.
 * Elle herite de CrudRepository et donc de ses methodes de CRUD
 *
 */
public interface EFGRepository extends CrudRepository<EFG, Integer> {

	
	/**
	 * Extrait de la base les exercices crees dans le canal d'id donne
	 * @param idCanal
	 * @return List<EFG>
	 */
	@Query(value = "SELECT e FROM EFG e WHERE e.createur.idCanal=?1")	
	public List<EFG> findByCanal(int idCanal);
	
	
	
	/**
	 * Extrait de la base le nombre de membres dans le canal d'id donne
	 * @param idCanal
	 * @return int
	 */
	@Query(value="SELECT COUNT(m) FROM MembreCanal m WHERE id_canal = ?1")
	public int membresCanal(int idCanal);
	
}
