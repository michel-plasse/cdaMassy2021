package fr.cdamassy2021.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cdamassy2021.entity.Canal;
import fr.cdamassy2021.repository.CanalRepository;

// fetch est une promesse , elle encpsule ... soit elle echoue soit elle reussie
// en cas de succes => the 
// fetch(url) cree un objet qui fait la requette ; elle a 2 methodes une en cas d'echec une autre en cas de succes


@Service
@Transactional
public class CanalService {
	
	@Autowired 
	CanalRepository repo;

	public void save(Canal canal) {
		repo.save(canal);
	}

	public List<Canal> listAll() {
		return (List<Canal>) repo.findAll();
	}

	public Canal get(int idCanal) {
		return repo.findById(idCanal).get();
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public List<Canal> search(String keyword) {
		return repo.search(keyword);

	}
}
