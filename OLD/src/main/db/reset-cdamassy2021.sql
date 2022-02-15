DELIMITER $$
DROP PROCEDURE IF EXISTS reset_cdamassy2021$$
CREATE definer='cdamassy2021_user'@localhost PROCEDURE reset_cdamassy2021(date_effet DATETIME)
BEGIN
	CALL truncate_all_tables();
  IF date_effet IS NULL THEN
		SET date_effet = NOW();
	END IF;
  BEGIN
    -- Recuperation en cas d'exception (intégrité, syntaxe)
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
      -- Annuler la transaction
      ROLLBACK;
			-- Propager l'exception
      RESIGNAL;
    END;  
    -- Demarrer une transaction : si une erreur se produit,
    -- tout sera annulé
    START TRANSACTION;
    -- 1er groupe : tableaux ne dépendant d'aucun autre
    INSERT INTO canal(id_canal, nom) VALUES
			(1, 'SIO 2021 projet'),
			(2, 'SIO 2021 java'),
			(3, 'CDA 2021');
 
		INSERT INTO personne(id_personne, prenom, nom, email, pwd, tel, est_formateur,
    est_gestionnaire, est_administrateur, url_photo, inscrit_a) VALUES
			-- 2 formateurs
			(1, 'Tryphon', 'Tournesol', 'formateur1@gmail.com', 'azerty', '0601020304', 1,
				0, 0, 'formateur1@gmail.com.jpg', date_effet - INTERVAL 3 MONTH),
			(2, 'Bianca', 'Castafiore', 'formateur2@gmail.com', 'azerty', '0611121314', 1,
				0, 0, 'formateur2@gmail.com.jpg', date_effet - INTERVAL 3 MONTH),
			-- 5 etudiants
			(3, 'Manuel', 'Rivière', 'etudiant1@gmail.com', 'azerty', '0621222324', 0,
				0, 0, 'etudiant1@gmail.com.jpg', date_effet - INTERVAL 2 MONTH),
			(4, 'Marguerite', 'Moulin', 'etudiant2@gmail.com', 'azerty', '0631323334', 0,
				0, 0, 'etudiant2@gmail.com.jpg', date_effet - INTERVAL 2 MONTH),
			(5, 'Nadia', 'Lupin', 'etudiant3@gmail.com', 'azerty', '0631323334', 0,
				0, 0, 'etudiant3@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY),
			(6, 'Marguerite', 'Gatot', 'etudiant4@gmail.com', 'azerty', '0641424344', 0,
				0, 0, 'etudiant4@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY),
			(7, 'Karim', 'Malingua', 'etudiant5@gmail.com', 'azerty', '0651525354', 0,
				0, 0, 'etudiant5@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY);
		INSERT INTO type_question(id_type_question, libelle) VALUES
		(1, 'oui/non'),
		(2, 'réponse libre'),
		(3, 'options multiples');
      
		-- Tableaux ne dépendant que du 1er groupe
		INSERT INTO	efg(id_efg, intitule, date_creation, id_createur, id_canal, groupes) VALUES
    -- 2 dans le canal 1 + 1 dans le canal 2, tous du formateur 1
			(1, 'TP d�finir objectif', date_effet - INTERVAL 1 MONTH, 1, 1,'2,3'),
			(2, 'TP cadrage', date_effet - INTERVAL 1 MONTH, 1, 1, '2,2'),
			(3, "TP tests d'acceptation", date_effet - INTERVAL 1 MONTH, 1, 2, '3,2,2');
		
		INSERT INTO question(id_question, libelle, id_canal, id_createur, 
    id_type_question, id_questionnaire) VALUES
			-- 1 de chaque type
			(1, 'Avez-vous fini�?', 3, 1, 1,null), -- oui/non
			(2, 'Combien de temps voulez-vous pour ce TP�?', 3, 1, 3,null), -- plusieurs choix 
			(3, 'Donnez un exemple de classe abstraite', 1, 1, 2,null), -- libre
			(4, "Chassez l'intrus",1,2,3,1), -- portée
			(5, 'Que vaut s, avec String s = "0" + 1 ?', 1, 1, 3,1), -- plusieurs choix 
			(6, "Quel fruit est un fruit d'hiver�?", 2, 1, 3,2), -- plusieurs choix 
			(7, "Quel légume est le plus riche en vitamine C�?", 2, 1, 3,2); -- plusieurs choix 
		
        INSERT INTO membre_canal(id_canal, id_personne, ajoute_a) VALUES
			-- les 2 formateurs dans canal 1
			(1, 1, date_effet - INTERVAL 2 MONTH),
			(1, 2, date_effet - INTERVAL 2 MONTH),
      -- tous les étudiants dans canal 1
			(1, 3, date_effet - INTERVAL 1 MONTH),
			(1, 4, date_effet - INTERVAL 1 MONTH),
			(1, 5, date_effet - INTERVAL 1 MONTH),
			(1, 6, date_effet - INTERVAL 1 MONTH),
			(1, 7, date_effet - INTERVAL 1 MONTH),
      -- canal 2
			(2, 1, date_effet - INTERVAL 2 MONTH),
			(2, 3, date_effet - INTERVAL 1 MONTH),
			(2, 4, date_effet - INTERVAL 1 MONTH),
			(2, 5, date_effet - INTERVAL 1 MONTH);
		INSERT INTO questionnaire(id_questionnaire, id_createur,id_canal, libelle) VALUES
			(1, 1, 1, 'Bases de Java'),
			(2, 1, 1, 'Fruits et légumes');


		INSERT INTO groupe_efg(id_efg, id_createur) VALUES
			-- 2 groupes par efg, avec 2 membres dans chaque (voir plus loin)
			(1, 3),
			(1, 4),
			(2, 3),
			(2, 5);

		INSERT INTO proposition(id_proposition, id_question, libelle, est_correcte) VALUES
			-- 2 questions dans les 2 questionnaires (1= Java, 2=fruits et légumes)
			(1, 4, 'private', 0), -- portée
			(2, 4, 'protege', 1),
			(3, 4, 'public', 0),
			(4, 5, '1', 0), -- Que vaut s, avec String s = "0" + 1 ?'
			(5, 5, '01', 1),
			(6, 5, 'erreur', 0),
			(7, 6, 'kaki', 1), -- Quel fruit est un fruit d'hiver ?
			(8, 6, 'melon', 0),
			(9, 6, 'orange', 1),
			(10, 7, 'chou', 1), -- Quel légume est le plus riche en vitamine C ?
			(11, 7, 'carotte', 0),
			(12, 7, 'epinard', 0),
            (13, 1, 'oui', 0),
            (14, 1, 'non', 0),
			-- sondages
			(15, 2, '1/2h', null),
			(16, 2, '1h', null),
			(17, 2, '1h30', null);
        
		INSERT INTO reponse(id_question, id_personne, libelle) VALUES
			-- question 1 avez-vous fini
      (1, 3, 'Oui'),
      (1, 4, 'Oui'),
      (1, 5, 'Non'),
      (1, 6, 'Oui'),
      (1, 7, 'Non'),
			-- question 2 Combien de temps voulez-vous pour ce TP (choix de 1 à 3)
      (2, 3, '1h30'),
      (2, 4, '1h'),
      (2, 5, '1h30'),
      (2, 6, '1h'),
      (2, 7, '1h30'),
      -- question 3 Donnez un exemple de classe abstraite
      (3, 3, 'java.util.List'),
      (3, 4, 'ArrayList'),
      (3, 5, 'java.sql.Connection'),
      (3, 6, 'je sais pas');
      -- 7e étudiant ne donne pas de réponse
		
		INSERT INTO membre_groupe_efg(id_personne, id_efg, id_createur) VALUES
			-- 2 groupes par efg, avec min 2 membres dans chaque
	  (3, 1, 3),
      (5, 1, 3),
      (6, 1, 3),
      (4, 1, 4),
      (7, 1, 4),
      -- 2e efg, avec un membre pas en groupe, et des groupes différents
      (3, 2, 3),
      (4, 2, 3),
      (5, 2, 5),
      (6, 2, 5);
	
      -- Valider tout
      COMMIT;
	END;
END$$

DROP PROCEDURE IF EXISTS reset_to_now$$
CREATE definer='cdamassy2021_user'@localhost PROCEDURE reset_to_now()
BEGIN
  CALL reset_cdamassy2021(NOW());
END$$

CALL reset_to_now()$$
