package fr.cdamassy2021.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cdamassy2021.dto.UserDTO;
import fr.cdamassy2021.entity.Personne;
import fr.cdamassy2021.entity.User;
import fr.cdamassy2021.exception.UserNotFoundException;
import fr.cdamassy2021.repository.PersonneRepository;
import fr.cdamassy2021.repository.UserRepository;


@Service
@Transactional
public class AuthService {
	@Autowired
	PersonneRepository personneRepo;

	@Transactional(readOnly = true)
	public UserDTO login(String username, String password) throws UserNotFoundException {
		System.out.println("username : "+ username);
		System.out.println("password : "+ password);
		Personne personne = personneRepo.findByEmail(username);
		if(null == personne) throw new UserNotFoundException();
		
		boolean passwordCorrect = BCrypt.checkpw(password, personne.getPwd());
		if(!passwordCorrect) throw new UserNotFoundException();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(personne.getIdPersonne());
		userDTO.setNom(personne.getNom());
		userDTO.setPrenom(personne.getPrenom());
		userDTO.setUsername(personne.getEmail());
		List<String> roles = personne.getRoles().stream().map(r ->{
			return r.getLibelle();
		}).collect(Collectors.toList());
		userDTO.setRoles(roles);
		return userDTO ;
	}
}
