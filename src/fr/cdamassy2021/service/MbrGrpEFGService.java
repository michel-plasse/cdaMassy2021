package fr.cdamassy2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.MbrGrpEFG;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.repository.MbrGrpEFGRepository;

@Service
public class MbrGrpEFGService {
	@Autowired
	MbrGrpEFGRepository mbrGrpEFGRepository;
	
	public Iterable<MbrGrpEFG> findAll() {	
		return mbrGrpEFGRepository.findAll();
	}
	
	public List<MbrGrpEFG> fonctionService() {	
		return mbrGrpEFGRepository.fonctionRepo();
	}

}
