package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cdamassy2021.entity.Canal;


public interface CanalRepository extends CrudRepository<Canal, Long> {
	
	@Query(value = "SELECT c FROM Canal c WHERE c.nom LIKE '%' || :keyword || '%'")
	public List<Canal> search(@Param("keyword") String keyword);
}