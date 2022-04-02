package fr.cdamassy2021.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Question;

@Repository
public interface DatabaseProceduresRepository extends JpaRepository<Question, Long>{
	@Transactional
	@Procedure(procedureName = "reset_to_now")
	public void resetDatabase();
}
