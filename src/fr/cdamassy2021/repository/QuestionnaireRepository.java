package fr.cdamassy2021.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
	// how
	@Query(value = "SELECT q FROM Questionnaire q WHERE q.libelle LIKE '%' || :keyword || '%'")
	public Set<Questionnaire> search(@Param("keyword") String keyword);
	
	@Query(value = "SELECT q FROM Questionnaire q WHERE q.idCanal=?1")
	public Set<Questionnaire> findByCanal(long idCanal);
}
