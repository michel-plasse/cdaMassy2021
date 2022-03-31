package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import fr.cdamassy2021.entity.MbrGrpEFG;
import fr.cdamassy2021.entity.Personne;

@Repository
public interface MbrGrpEFGRepository extends CrudRepository<MbrGrpEFG, Long> {
	
//	@Query(value="select m from MbrGrpEFG m where m.personne.idPersonne=3 ")
//	List<MbrGrpEFG> fonctionRepo();
	@Query(value="select m from MbrGrpEFG m where m.personne.idPersonne=3 ")
	fonctionRepo();

}
