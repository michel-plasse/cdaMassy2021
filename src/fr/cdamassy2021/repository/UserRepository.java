package fr.cdamassy2021.repository;

import org.springframework.data.repository.CrudRepository;

import fr.cdamassy2021.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
