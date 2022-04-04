package fr.cdamassy2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.dto.LoginDTO;
import fr.cdamassy2021.dto.UserDTO;
import fr.cdamassy2021.service.AuthService;
import fr.cdamassy2021.service.JWTService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@Autowired
	JWTService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO){
		try {
			UserDTO userDTO = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
			System.err.println(userDTO);
			String res = jwtService.getJWT(userDTO.getUsername(), userDTO);
			System.err.println(res);
			userDTO.setAccessToken(res);
			return ResponseEntity.ok(userDTO);
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@PostMapping("/register")
//	public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO userDTO) {
		
//		//tester si User est valide
//		if (errors.hasErrors()) {
//			mv.addObject("user", user);
//			mv.setViewName("user/showForm");
//		} else {
//			String pass = userDTO.getPassword();
//			String encodedPassword = encoder.encode(user.getPassword());
//			user.setPassword(encodedPassword);
//			//crer un set
//			Set<Role> roles = new HashSet<>();
//			//rcuprer le role avec le libelle "USER" et l'ajouter dans set
//			Role roleUser = roleService.findByLibelle("USER");
//			roles.add(roleUser);
//			user.setRoles(roles);
//			userService.create(user);
//			//envoyer mail de confirmation
//			String body = "Nom d'utilisateur : " + user.getUsername() + "\n";
//			body += "Mot de passe : " + pass;
//			emailUtil.sendMail(user.getMail(), "Inscription", body);
//			mv.setViewName("redirect:/admin/user/list");
//		}
//		return mv;
//	}
}