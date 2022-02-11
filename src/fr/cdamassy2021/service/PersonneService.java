package fr.cdamassy2021.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cdamassy2021.entity.Customer;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.repository.PersonneRepository;

@Service
@Transactional
public class PersonneService {
	@Autowired
	PersonneRepository repo;

	public void save(Personne personne) {
		repo.save(personne);
	}

	public List<Personne> listAll() {
		return (List<Personne>) repo.findAll();
	}
	
	public Collection<Personne> listMembreByCanal(){
		return repo.findMembreByCanal();
	}
	

}
