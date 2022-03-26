package fr.cdamassy2021.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.Question;
import fr.cdamassy2021.entity.Reponse;
import fr.cdamassy2021.repository.PersonneRepository;
import fr.cdamassy2021.repository.QuestionRepository;
import fr.cdamassy2021.repository.ReponseRepository;
@Service
public class ReponseService {
	@Autowired
	private ReponseRepository repo;
	
	@Autowired 
	private PersonneRepository personRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	public void save(Reponse reponse) {
		repo.save(reponse);
	}

	public Reponse save(long idAuteur, long idQuestion, String libelle) {
		Personne autor = personRepo.findById(idAuteur).get();
		Question question = questionRepo.findById(idQuestion).get();
		//tryData:
		boolean valid = 
				(autor!=null 
				&& libelle!=""
				&& libelle!=null 
				&& question!=null)
				?true:false; 

		//valid true:
		if (valid) {
			Reponse reponse = new Reponse(autor, question, libelle);
			save(reponse);
			return reponse;
		}
		return null;
	}
}
