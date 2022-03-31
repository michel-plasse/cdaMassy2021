package fr.cdamassy2021.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.GrpEFG;
import fr.cdamassy2021.service.GrpEFGRepository;

@Service
@Transactional
public class GrpEFGService {
	
	@Autowired
	GrpEFGRepository grpEFGRepository;
	
	public Iterable<GrpEFG> findAll() {
		
		return grpEFGRepository.findAll();
	}
	
	public Optional<GrpEFG> findById(int idEfg) {
		
		return  grpEFGRepository.findById((long) idEfg);
	}


}
