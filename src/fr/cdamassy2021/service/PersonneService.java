package fr.cdamassy2021.service;


import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.repository.PersonneRepository;

@Service
@Transactional
public class PersonneService {

	@Autowired 
	PersonneRepository repo;
	
	public Personne exist(String email, String pwd) {
		return repo.exist(email,pwd);
	}

	public void save(Personne personne) {
		repo.save(personne);
	}

	public List<Personne> listAll() {
		return (List<Personne>) repo.findAll();
	}
	
	public Collection<Personne> listMembreByCanal(int idCanal){
		return repo.findMembreByCanal(idCanal);
	}

	public void enleverMembreDuCanal(int idMembreAEffacer,int idCanal) {
		repo.SupprimerMembrDuCanal(idMembreAEffacer, idCanal);
	}
	
	public void ajouterMembreDuCanal(int idMembre,int idCanal) {
		 repo.AjouterMembreAuCanal(idMembre, idCanal);
	}

}
