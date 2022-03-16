USE `cdamassy2021` ;

-- -----------------------------------------------------
-- procedures utilitaires
-- -----------------------------------------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS apply_to_all_tables$$
CREATE PROCEDURE `apply_to_all_tables`(p_prefix VARCHAR(45), p_suffix VARCHAR(128))
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


DROP PROCEDURE IF EXISTS drop_all_tables$$
CREATE PROCEDURE `drop_all_tables`()
BEGIN
	CALL apply_to_all_tables('DROP TABLE', '');
END$$


DROP PROCEDURE IF EXISTS reset_auto_increment_all_tables$$
CREATE PROCEDURE `reset_auto_increment_all_tables`()
BEGIN
	CALL apply_to_all_tables('ALTER TABLE', 'AUTO_INCREMENT 1');
END$$


DROP PROCEDURE IF EXISTS truncate_all_tables$$
CREATE PROCEDURE `truncate_all_tables`()
BEGIN
	CALL apply_to_all_tables('TRUNCATE TABLE', '');
END$$



-- -----------------------------------------------------
-- Recréation des tableaux 
-- -----------------------------------------------------
DELIMITER ;
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
