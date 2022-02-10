package fr.cdamassy2021.repository;

import org.springframework.data.repository.CrudRepository;

import fr.cdamassy2021.entity.Personne;

public interface PersonneRepository extends CrudRepository<Personne, Long> {

}
