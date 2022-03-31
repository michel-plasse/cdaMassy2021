package fr.cdamassy2021.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.GrpEFG;

@Repository
public interface GrpEFGRepository extends CrudRepository<GrpEFG, Long> {
	
	
}
