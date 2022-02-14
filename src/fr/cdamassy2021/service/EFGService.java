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
import fr.cdamassy2021.repository.EFGRepository;

/**
 * 
 * @author TeamVerte
 * Classe responsable des methodes permettant la
 * realisation des fonctionnalites de l'application
 */
@Service
@Transactional
public class EFGService {

	@Autowired
	EFGRepository repo;

	/**
	 * retourne la liste des efgs d'un canal
	 * @param idCanal
	 * @return List<EFG>
	 */
	public List<EFG> listByCanal(int idCanal) {
		List<EFG> EfgList = repo.findByCanal(idCanal);
		return EfgList;
	}
	/**
	 * retourne l'efg d'id donne
	 * @param idEFG
	 * @return EFG
	 */
	public EFG findById(int idEFG) {
		EFG efg = repo.findById(idEFG).get();
		return efg;
	}

	/**
	 * enregistre l'efg donne dans la base de donnees
	 * @param efg
	 * 
	 */
	public EFG saveEFG(EFG efg){
		return repo.save(efg);
		
	}
	/**
	 * retourne le nombre de membres d'un canal
	 * @param idCanal
	 * @return int
	 */
	public int nombreMembresCanal(int idCanal) {
		return repo.membresCanal(idCanal);
	}
}
