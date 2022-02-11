package fr.cdamassy2021.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.repository.EFGRepository;

@Service
@Transactional
public class EFGService {

	@Autowired
	EFGRepository repo;

	public List<EFG> listByCanal(int idCanal) {
		List<EFG> EfgList = repo.findByCanal(idCanal);
		return EfgList;
	}
	
	public EFG findById(int idEFG) {
		EFG efg = repo.findById(idEFG).get();
		return efg;
	}


}
