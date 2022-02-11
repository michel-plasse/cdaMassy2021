package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	// how to search for creator then?
	@Query(value = "SELECT q FROM Question q WHERE q.libelle LIKE '%' || :keyword || '%'")
	public List<Question> search(@Param("keyword") String keyword);
	
	@Query(value = "SELECT q FROM Question q WHERE q.idCanal=?1")	
	public List<Question> findByCanal(long idCanal);
}