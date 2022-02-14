package fr.cdamassy2021.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Proposition;
import fr.cdamassy2021.repository.PropositionRepository;

@Service
@Transactional
public class PropositionService {

	@Autowired
	PropositionRepository propositionRepo;
	
	public Proposition save(Proposition prop) {
		return propositionRepo.save(prop);
	}
}
