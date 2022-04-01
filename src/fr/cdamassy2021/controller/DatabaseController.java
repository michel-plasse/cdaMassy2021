package fr.cdamassy2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.cdamassy2021.repository.DatabaseProceduresRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/database")
public class DatabaseController {

	@Autowired
    DatabaseProceduresRepository databaseRepo;
   
 	/*
 	 * POST: /api/database/reset -> renvoie reponse crée
 	 */
 	@RequestMapping(value="/reset",
 					method = RequestMethod.POST)
 	public ResponseEntity<String> resetDatabase(){
 		try {
 			databaseRepo.resetDatabase();
 			String message = "Database reset done";
 			return ResponseEntity.ok(message);
 		}
 		catch(Exception e) {
 			String message = "Database reset failed";
 			return ResponseEntity.badRequest().body(message);
 		}
 	}
}