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
	
	@Query(value = "SELECT q.*, p.prenom, p.nom\n"
            + "FROM question q\n"
            + "	INNER JOIN \n"
            + "		personne p\n"
            + "			ON p.id_personne = q.id_createur\n"
            + "WHERE NOT EXISTS(\n"
            + "	SELECT r.id_question\n"
            + "    FROM reponse r\n"
            + "    WHERE r.id_question = q.id_question AND r.id_personne = ?\n" 
            + ") AND id_canal = ?;"
            , nativeQuery=true)
	public List<Question> findPending(long idUser, long idCanal);
}