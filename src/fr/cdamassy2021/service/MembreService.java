package fr.cdamassy2021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.repository.MembreRepository;

@Service
public class MembreService {

	@Autowired
	MembreRepository repo;

	public List<Personne> listeMembreDuCanal(int idCanal){
		return (List<Personne>) repo.findMembreByCanal(idCanal);
	}

}
