package fr.cdamassy2021.service;

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
}
