package fr.cdamassy2021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.EFG;
import fr.cdamassy2021.repository.EFGRepository;

@Service
public class EFGService {
	
	@Autowired
	EFGRepository repo;
	
	public List<EFG> listAll() {
	
		
		List<EFG> EfgList = repo.findAll();
		System.out.println("EFG Service");
		System.out.println(EfgList);
		
		return EfgList;
		
	}
	

	public List<EFG> listByCanal(int idCanal) {
		List<EFG> EfgList = repo.findByCanal(idCanal);
		System.out.println("EFG Service");
		System.out.println(EfgList);
		
		return EfgList;
	}
	
}
