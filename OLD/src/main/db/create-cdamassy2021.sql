USE `cdamassy2021` ;

CALL drop_all_tables();

-- -----------------------------------------------------
-- Table `personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` INT(11) NOT NULL AUTO_INCREMENT,
  `prenom` VARCHAR(45) NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `pwd` VARCHAR(45) NOT NULL,
  `tel` VARCHAR(45) NOT NULL,
  `est_formateur` TINYINT NOT NULL DEFAULT 0,
  `est_gestionnaire` TINYINT NOT NULL DEFAULT 0,
  `est_administrateur` TINYINT NOT NULL DEFAULT 0,
  `url_photo` VARCHAR(65) NULL,
  `token` VARCHAR(255) NULL,
  `inscrit_a` VARCHAR(45) NULL,
  PRIMARY KEY (`id_personne`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `canal` (
  `id_canal` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_canal`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire` (
  `id_questionnaire` INT NOT NULL AUTO_INCREMENT,
  `id_createur` INT(11) NOT NULL,
  `libelle` VARCHAR(45) NOT NULL,
  `id_canal` INT NOT NULL,
  `date_ajout` DATETIME NOT NULL DEFAULT now(),
  PRIMARY KEY (`id_questionnaire`),
  INDEX `fk_questionnaire_personne_idx` (`id_createur` ASC),
  INDEX `fk_questionnaire_canal1_idx` (`id_canal` ASC),
  CONSTRAINT `fk_questionnaire_personne`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questionnaire_canal1`
    FOREIGN KEY (`id_canal`)
    REFERENCES `canal` (`id_canal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Le questionnaire géré par un formateur => trigger';


-- -----------------------------------------------------
-- Table `membre_canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `membre_canal` (
  `id_canal` INT NOT NULL,
  `id_personne` INT(11) NOT NULL,
  `ajoute_a` DATETIME NOT NULL,
  PRIMARY KEY (`id_canal`, `id_personne`),
  INDEX `fk_canal_personne_personne1_idx` (`id_personne` ASC),
  INDEX `fk_canal_personne_canal1_idx` (`id_canal` ASC),
  CONSTRAINT `fk_membre_canal_canal`
    FOREIGN KEY (`id_canal`)
    REFERENCES `canal` (`id_canal`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_membre_canal_personne`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `efg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efg` (
  `id_efg` INT NOT NULL AUTO_INCREMENT,
  `intitule` VARCHAR(45) NULL,
  `date_creation` DATETIME NOT NULL DEFAULT now(),
  `id_createur` INT(11) NOT NULL,
  `id_canal` INT NOT NULL,
  `groupes`VARCHAR(150) NULL,
  PRIMARY KEY (`id_efg`),
  INDEX `fk_efg_membre_canal_id` (`id_canal` ASC, `id_createur` ASC),
  CONSTRAINT `fk_efg_membre_canal1`
    FOREIGN KEY (`id_canal` , `id_createur`)
    REFERENCES `membre_canal` (`id_canal` , `id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `type_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `type_question` (
  `id_type_question` INT NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_type_question`),
  UNIQUE INDEX `libelle_UNIQUE` (`libelle` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question` (
  `id_question` INT NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(128) NOT NULL,
  `id_canal` INT NOT NULL,
  `id_createur` INT NOT NULL,
  `id_type_question` INT NOT NULL,
  `id_questionnaire` INT NULL,
  PRIMARY KEY (`id_question`),
  INDEX `fk_question_personne_idx` (`id_createur` ASC),
  INDEX `fk_question_canal_idx` (`id_canal` ASC),
  INDEX `fk_question_type_question_idx` (`id_type_question` ASC),
  INDEX `fk_question_questionnaire1_idx` (`id_questionnaire` ASC),
  CONSTRAINT `fk_question_personne`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_canal`
    FOREIGN KEY (`id_canal`)
    REFERENCES `canal` (`id_canal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_type_question`
    FOREIGN KEY (`id_type_question`)
    REFERENCES `type_question` (`id_type_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_questionnaire1`
    FOREIGN KEY (`id_questionnaire`)
    REFERENCES `questionnaire` (`id_questionnaire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proposition` (
  `id_proposition` INT NOT NULL AUTO_INCREMENT,
  `id_question` INT NOT NULL,
  `libelle` VARCHAR(128) NOT NULL,
  `est_correcte` TINYINT NULL,
  PRIMARY KEY (`id_proposition`),
  INDEX `fk_option_question_question1_idx` (`id_question` ASC),
  CONSTRAINT `fk_option_question_question`
    FOREIGN KEY (`id_question`)
    REFERENCES `question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reponse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reponse` (
  `id_question` INT NOT NULL,
  `id_personne` INT(11) NOT NULL,
  `id_proposition` INT NULL,
  `libelle` VARCHAR(45) NULL,
  `date_rendu` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`id_question`, `id_personne`),
  INDEX `fk_reponse_quesion_personne_idx` (`id_personne` ASC),
  INDEX `fk_reponse_question_question_idx` (`id_question` ASC),
  INDEX `fk_reponse_proposition1_idx` (`id_proposition` ASC),
  CONSTRAINT `fk_reponse_question_question`
    FOREIGN KEY (`id_question`)
    REFERENCES `question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reponse_question_personne`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reponse_proposition1`
    FOREIGN KEY (`id_proposition`)
    REFERENCES `proposition` (`id_proposition`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `groupe_efg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `groupe_efg` (
  `id_createur` INT(11) NOT NULL,
  `id_efg` INT NOT NULL,
  PRIMARY KEY (`id_createur`, `id_efg`),
  INDEX `fk_personne_efg_efg1_idx` (`id_efg` ASC),
  INDEX `fk_personne_efg_personne1_idx` (`id_createur` ASC),
  CONSTRAINT `fk_personne_efg_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_efg_efg1`
    FOREIGN KEY (`id_efg`)
    REFERENCES `efg` (`id_efg`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `copie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copie` (
  `id_questionnaire` INT NOT NULL,
  `id_personne` INT(11) NOT NULL,
  `date_demarrage` DATETIME NOT NULL DEFAULT now(),
  PRIMARY KEY (`id_questionnaire`, `id_personne`),
  INDEX `fk_questionnaire_personne_personne1_idx` (`id_personne` ASC),
  INDEX `fk_questionnaire_personne_questionnaire1_idx` (`id_questionnaire` ASC),
  CONSTRAINT `fk_questionnaire_personne_questionnaire1`
    FOREIGN KEY (`id_questionnaire`)
    REFERENCES `questionnaire` (`id_questionnaire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questionnaire_personne_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `membre_groupe_efg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `membre_groupe_efg` (
  `id_personne` INT(11) NOT NULL,
  `id_createur` INT(11) NOT NULL,
  `id_efg` INT NOT NULL,
  PRIMARY KEY (`id_personne`, `id_createur`, `id_efg`),
  INDEX `fk_personne_efg_personne2_idx` (`id_personne` ASC),
  INDEX `fk_membre_groupe_efg_groupe_efg1_idx` (`id_createur` ASC, `id_efg` ASC),
  CONSTRAINT `fk_personne_efg_personne2`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_membre_groupe_efg_groupe_efg1`
    FOREIGN KEY (`id_createur` , `id_efg`)
    REFERENCES `groupe_efg` (`id_createur` , `id_efg`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `cdamassy2021` ;

-- -----------------------------------------------------
-- procedure apply_to_all_tables
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `apply_to_all_tables`(p_prefix VARCHAR(45), p_suffix VARCHAR(128))
BEGIN
	-- to exit the cursor loop
	DECLARE v_ended BOOLEAN DEFAULT FALSE;
  -- sql statement for each table
	DECLARE v_sql VARCHAR(64);
  -- truncate statements (one for each table)
  DECLARE v_stmts CURSOR FOR 
		SELECT concat(p_prefix, ' ', table_name, ' ', p_suffix)
		FROM information_schema.tables	
		WHERE table_schema = DATABASE() AND table_type = 'BASE TABLE'
    ORDER BY table_name ASC;  
	-- truncate or drop require disabling referential integrity constraints
	SET FOREIGN_KEY_CHECKS = 0;
  OPEN v_stmts;
  BEGIN
    DECLARE EXIT HANDLER FOR NOT FOUND SET v_ended = TRUE;
    REPEAT
      FETCH v_stmts INTO v_sql;
      -- Need to use a user-defined variable
      SET @sql = v_sql;
      -- 3 statements to execute the truncate statement!
      PREPARE v_stmt FROM @sql;
      EXECUTE v_stmt;
			DEALLOCATE PREPARE v_stmt;
		UNTIL v_ended END REPEAT; 
  END;
  CLOSE v_stmts;
  -- Enabling again the referential integrity constraints
	SET FOREIGN_KEY_CHECKS = 0;
  -- SELECT 'All tables of current schema truncated' AS log;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure drop_all_tables
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_all_tables`()
BEGIN
	CALL apply_to_all_tables('DROP TABLE', '');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure reset_auto_increment_all_tables
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_auto_increment_all_tables`()
BEGIN
	CALL apply_to_all_tables('ALTER TABLE', 'AUTO_INCREMENT 1');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure reset_cdamassy2021
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_cdamassy2021`(date_effet DATETIME)
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
		INSERT INTO	efg(id_efg, intitule, date_creation, id_createur, id_canal) VALUES
    -- 2 dans le canal 1 + 1 dans le canal 2, tous du formateur 1
			(1, 'TP définir objectif', date_effet - INTERVAL 1 MONTH, 1, 1),
			(2, 'TP cadrage', date_effet - INTERVAL 1 MONTH, 1, 1),
			(3, "TP tests d'acceptation", date_effet - INTERVAL 1 MONTH, 1, 2);
		
		INSERT INTO question(id_question, libelle, id_canal, id_createur, 
    id_type_question, id_questionnaire) VALUES
			-- 1 de chaque type
			(1, 'Avez-vous fini ?', 1, 1, 1,null), -- oui/non
			(2, 'Combien de temps voulez-vous pour ce TP ?', 1, 1, 3,null), -- plusieurs choix 
			(3, 'Donnez un exemple de classe abstraite', 1, 1, 2,null), -- libre
			(4, "Chassez l'intrus",1,2,3,1), -- portée
			(5, 'Que vaut s, avec String s = "0" + 1 ?', 1, 1, 3,1), -- plusieurs choix 
			(6, "Quel fruit est un fruit d'hiver ?", 1, 1, 3,2), -- plusieurs choix 
			(7, "Quel légume est le plus riche en vitamine C ?", 1, 1, 3,2); -- plusieurs choix 
		
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
      (1, 1, 'private', 0), -- portée
      (2, 1, 'protege', 1),
      (3, 1, 'public', 0),
      (4, 2, '1', 0), -- Que vaut s, avec String s = "0" + 1 ?'
      (5, 2, '01', 1),
      (6, 2, 'erreur', 0),
      (7, 3, 'kaki', 1), -- Quel fruit est un fruit d'hiver ?
      (8, 3, 'melon', 0),
      (9, 3, 'orange', 1),
      (10, 4, 'chou', 1), -- Quel légume est le plus riche en vitamine C ?
      (11, 4, 'carotte', 0),
      (12, 4, 'epinard', 0),
			-- sondages
      (13, 2, '1/2h', null),
      (14, 2, '1h', null),
      (15, 2, '1h30', null);
        
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

DELIMITER ;

-- -----------------------------------------------------
-- procedure reset_to_now
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_to_now`()
BEGIN
  CALL reset_cdamassy2021(NOW());
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure truncate_all_tables
-- -----------------------------------------------------

DELIMITER $$
USE `cdamassy2021`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `truncate_all_tables`()
BEGIN
	CALL apply_to_all_tables('TRUNCATE TABLE', '');
END$$

DELIMITER ;
