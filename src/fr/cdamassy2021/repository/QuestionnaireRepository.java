package fr.cdamassy2021.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import fr.cdamassy2021.entity.Questionnaire;


=======
import entity.Questionnaire;
>>>>>>> branch 'main' of local repository

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
// how to search for creator then?
	@Query(value = "SELECT q FROM Questionnaire q WHERE q.libelle LIKE '%' || :keyword || '%'")
	public List<Questionnaire> search(@Param("keyword") String keyword);
}