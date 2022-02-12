package fr.cdamassy2021.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Reponse;
import fr.cdamassy2021.repository.ReponseRepository;
@Service
public class ReponseService {
	@Autowired
	private ReponseRepository repo;
	
	public void save(Reponse reponse) {
		repo.save(reponse);
	}
}
