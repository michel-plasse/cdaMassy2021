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
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.repository.EFGRepository;
import fr.cdamassy2021.repository.PersonneRepository;

@Service
@Transactional
public class EFGService {

	@Autowired
	EFGRepository repo;
	
	@Autowired
	PersonneRepository repoPersonne;

	public Optional<List<EFG>> listByCanal(int idCanal) {
		Optional<List<EFG>> EfgList = repo.findByCanal(idCanal);
		return EfgList;
	}
	
	public Optional<EFG> findById(int idEFG) {
		Optional<EFG> efg = repo.findById(idEFG);
		return efg;
	}

	
	public EFG saveEFG(EFG efg){
		return repo.save(efg);
		
	}
	
	public int nombreMembresCanal(int idCanal) {
		return repo.membresCanal(idCanal);
	}
	
	public Optional<Personne> getCreateur(int idEFG) {
		Optional<EFG> optEFG = this.findById(idEFG);
		if (optEFG.isPresent()) {
			EFG efg = optEFG.get();
			return repoPersonne.findById((long) efg.getIdCreateur());
		}else {
			return null;
		}
	}
}
