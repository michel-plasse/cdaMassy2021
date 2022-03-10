package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Canal;

@Repository
public interface CanalRepository extends CrudRepository<Canal, Long> {

	@Query(value = "SELECT c FROM Canal c WHERE c.nom LIKE '%' || :keyword || '%'")
	public List<Canal> search(@Param("keyword") String keyword);

	@Query(value = "SELECT nom, ca.id_canal\n"
			+ "FROM membre_canal \n"
			+ "            INNER JOIN canal ca\n"
			+ "                    ON membre_canal.id_canal = ca.id_canal\n"
			+ "WHERE id_personne=?\n"
			+ "ORDER BY ca.id_canal",nativeQuery = true)
	public List<Canal> ListCanauxByIdMemebre(int IdMembre);

}