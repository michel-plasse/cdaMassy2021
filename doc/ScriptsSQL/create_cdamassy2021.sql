-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 02 avr. 2022 à 19:18
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cdamassy2021`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `apply_to_all_tables`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `apply_to_all_tables` (`p_prefix` VARCHAR(45), `p_suffix` VARCHAR(128))  BEGIN
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

DROP PROCEDURE IF EXISTS `drop_all_tables`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `drop_all_tables` ()  BEGIN
	CALL apply_to_all_tables('DROP TABLE', '');
END$$

DROP PROCEDURE IF EXISTS `reset_auto_increment_all_tables`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset_auto_increment_all_tables` ()  BEGIN
	CALL apply_to_all_tables('ALTER TABLE', 'AUTO_INCREMENT 1');
END$$

DROP PROCEDURE IF EXISTS `reset_cdamassy2021`$$
CREATE DEFINER=`cdamassy2021_user`@`localhost` PROCEDURE `reset_cdamassy2021` (`date_effet` DATETIME)  BEGIN
  CALL truncate_all_tables();
  IF date_effet IS NULL THEN
    SET date_effet = NOW();
  END IF;
  BEGIN
    -- Recuperation en cas d'exception (intÃ©gritÃ©, syntaxe)
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
      -- Annuler la transaction
      ROLLBACK;
      -- Propager l'exception
      RESIGNAL;
    END;  
    -- Demarrer une transaction : si une erreur se produit,
    -- tout sera annulÃ©
    START TRANSACTION;
    -- 1er groupe : tableaux ne dÃ©pendant d'aucun autre
    INSERT INTO canal(id_canal, nom) VALUES
			(1, 'SIO 2021 projet'),
			(2, 'SIO 2021 java'),
			(3, 'CDA 2021');
 
		INSERT INTO personne(id_personne, prenom, nom, email, pwd, tel, est_formateur,
    est_gestionnaire, est_administrateur, url_photo, inscrit_a) VALUES
			-- 2 formateurs
			(1, 'Tryphon', 'Tournesol', 'formateur1@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0601020304', 1,
				0, 0, 'formateur1@gmail.com.jpg', date_effet - INTERVAL 3 MONTH),
			(2, 'Bianca', 'Castafiore', 'formateur2@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0611121314', 1,
				0, 0, 'formateur2@gmail.com.jpg', date_effet - INTERVAL 3 MONTH),
			-- 5 etudiants
			(3, 'Manuel', 'Rivière', 'etudiant1@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0621222324', 0,
				0, 0, 'etudiant1@gmail.com.jpg', date_effet - INTERVAL 2 MONTH),
			(4, 'Marguerite', 'Moulin', 'etudiant2@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0631323334', 0,
				0, 0, 'etudiant2@gmail.com.jpg', date_effet - INTERVAL 2 MONTH),
			(5, 'Nadia', 'Lupin', 'etudiant3@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0631323334', 0,
				0, 0, 'etudiant3@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY),
			(6, 'Marguerite', 'Gatot', 'etudiant4@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0641424344', 0,
				0, 0, 'etudiant4@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY),
			(7, 'Karim', 'Malingua', 'etudiant5@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0651525354', 0,
				0, 0, 'etudiant5@gmail.com.jpg', date_effet - INTERVAL 1 MONTH - INTERVAL 15 DAY);
		INSERT INTO type_question(id_type_question, libelle) VALUES
		(1, 'oui/non'),
		(2, 'rÃ©ponse libre'),
		(3, 'options multiples');
      
		-- Tableaux ne dÃ©pendant que du 1er groupe
		INSERT INTO	efg(id_efg, intitule, date_creation, id_createur, id_canal, groupes) VALUES
    -- 2 dans le canal 1 + 1 dans le canal 2, tous du formateur 1
			(1, 'TP dÃ©finir objectif', date_effet - INTERVAL 1 MONTH, 1, 1,'3,2'),
			(2, 'TP cadrage', date_effet - INTERVAL 1 MONTH, 1, 1, '2,2'),
			(3, "TP tests d'acceptation", date_effet - INTERVAL 1 MONTH, 1, 2, '3,2,2');
		
		INSERT INTO question(id_question, libelle, id_canal, id_createur, 
    id_type_question, id_questionnaire) VALUES
			-- 1 de chaque type
			(1, 'Avez-vous finiï¿½?', 3, 1, 1,null), -- oui/non
			(2, 'Combien de temps voulez-vous pour ce TPï¿½?', 3, 1, 3,null), -- plusieurs choix 
			(3, 'Donnez un exemple de classe abstraite', 1, 1, 2,null), -- libre
			(4, "Chassez l'intrus",1,2,3,1), -- portÃ©e
			(5, 'Que vaut s, avec String s = "0" + 1 ?', 1, 1, 3,1), -- plusieurs choix 
			(6, "Quel fruit est un fruit d'hiverï¿½?", 2, 1, 3,2), -- plusieurs choix 
			(7, "Quel lÃ©gume est le plus riche en vitamine Cï¿½?", 2, 1, 3,2); -- plusieurs choix 
		
        INSERT INTO membre_canal(id_canal, id_personne, ajoute_a) VALUES
			-- les 2 formateurs dans canal 1
			(1, 1, date_effet - INTERVAL 2 MONTH),
			(1, 2, date_effet - INTERVAL 2 MONTH),
      -- tous les Ã©tudiants dans canal 1
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
			(2, 1, 1, 'Fruits et lÃ©gumes');


		INSERT INTO groupe_efg(id_efg, id_createur) VALUES
			-- 2 groupes par efg, avec 2 membres dans chaque (voir plus loin)
			(1, 3),
			(1, 4),
			(2, 3),
			(2, 5);

		INSERT INTO proposition(id_proposition, id_question, libelle, est_correcte) VALUES
			-- 2 questions dans les 2 questionnaires (1= Java, 2=fruits et lÃ©gumes)
			(1, 4, 'private', 0), -- portÃ©e
			(2, 4, 'protege', 1),
			(3, 4, 'public', 0),
			(4, 5, '1', 0), -- Que vaut s, avec String s = "0" + 1 ?'
			(5, 5, '01', 1),
			(6, 5, 'erreur', 0),
			(7, 6, 'kaki', 1), -- Quel fruit est un fruit d'hiver ?
			(8, 6, 'melon', 0),
			(9, 6, 'orange', 1),
			(10, 7, 'chou', 1), -- Quel lÃ©gume est le plus riche en vitamine C ?
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
			-- question 2 Combien de temps voulez-vous pour ce TP (choix de 1 Ã  3)
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
      -- 7e Ã©tudiant ne donne pas de rÃ©ponse
		
		INSERT INTO membre_groupe_efg(id_personne, id_efg, id_createur) VALUES
      	-- 2 groupes par efg, avec min 2 membres dans chaque
	(3, 1, 3),
      (5, 1, 3),
      (6, 1, 3),
      (4, 1, 4),
      (7, 1, 4),
      -- 2e efg, avec un membre pas en groupe, et des groupes diffÃƒÂ©rents
      (3, 2, 3),
      (4, 2, 3),
      (5, 2, 5),
      (6, 2, 5);
      
      INSERT INTO role (id, libelle) VALUES
(2, 'ROLE_FORMATEUR'),
(1, 'ROLE_USER');

INSERT INTO personne_role (personnes_id, roles_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1);
	      -- Valider tout
      COMMIT;
	END;
END$$

DROP PROCEDURE IF EXISTS `reset_to_now`$$
CREATE DEFINER=`cdamassy2021_user`@`localhost` PROCEDURE `reset_to_now` ()  BEGIN
  CALL reset_cdamassy2021(NOW());
END$$

DROP PROCEDURE IF EXISTS `truncate_all_tables`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `truncate_all_tables` ()  BEGIN
	CALL apply_to_all_tables('TRUNCATE TABLE', '');
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `canal`
--

DROP TABLE IF EXISTS `canal`;
CREATE TABLE IF NOT EXISTS `canal` (
  `id_canal` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  PRIMARY KEY (`id_canal`),
  UNIQUE KEY `nom_UNIQUE` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `canal`
--

INSERT INTO `canal` (`id_canal`, `nom`) VALUES
(3, 'CDA 2021'),
(2, 'SIO 2021 java'),
(1, 'SIO 2021 projet');

-- --------------------------------------------------------

--
-- Structure de la table `copie`
--

DROP TABLE IF EXISTS `copie`;
CREATE TABLE IF NOT EXISTS `copie` (
  `id_questionnaire` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `date_demarrage` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_questionnaire`,`id_personne`),
  KEY `fk_questionnaire_personne_personne1_idx` (`id_personne`),
  KEY `fk_questionnaire_personne_questionnaire1_idx` (`id_questionnaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `efg`
--

DROP TABLE IF EXISTS `efg`;
CREATE TABLE IF NOT EXISTS `efg` (
  `id_efg` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(45) DEFAULT NULL,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_createur` int(11) NOT NULL,
  `id_canal` int(11) NOT NULL,
  `groupes` varchar(150) NOT NULL,
  PRIMARY KEY (`id_efg`),
  KEY `fk_efg_membre_canal_id` (`id_canal`,`id_createur`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `efg`
--

INSERT INTO `efg` (`id_efg`, `intitule`, `date_creation`, `id_createur`, `id_canal`, `groupes`) VALUES
(1, 'TP dÃ©finir objectif', '2022-03-02 20:58:24', 1, 1, '3,2'),
(2, 'TP cadrage', '2022-03-02 20:58:24', 1, 1, '2,2'),
(3, 'TP tests d\'acceptation', '2022-03-02 20:58:24', 1, 2, '3,2,2');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_efg`
--

DROP TABLE IF EXISTS `groupe_efg`;
CREATE TABLE IF NOT EXISTS `groupe_efg` (
  `id_createur` int(11) NOT NULL,
  `id_efg` int(11) NOT NULL,
  PRIMARY KEY (`id_createur`,`id_efg`),
  KEY `fk_personne_efg_efg1_idx` (`id_efg`),
  KEY `fk_personne_efg_personne1_idx` (`id_createur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe_efg`
--

INSERT INTO `groupe_efg` (`id_createur`, `id_efg`) VALUES
(3, 1),
(4, 1),
(3, 2),
(5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `membre_canal`
--

DROP TABLE IF EXISTS `membre_canal`;
CREATE TABLE IF NOT EXISTS `membre_canal` (
  `id_canal` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `ajoute_a` datetime NOT NULL,
  PRIMARY KEY (`id_canal`,`id_personne`),
  KEY `fk_canal_personne_personne1_idx` (`id_personne`),
  KEY `fk_canal_personne_canal1_idx` (`id_canal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `membre_canal`
--

INSERT INTO `membre_canal` (`id_canal`, `id_personne`, `ajoute_a`) VALUES
(1, 1, '2022-02-02 20:58:24'),
(1, 2, '2022-02-02 20:58:24'),
(1, 3, '2022-03-02 20:58:24'),
(1, 4, '2022-03-02 20:58:24'),
(1, 5, '2022-03-02 20:58:24'),
(1, 6, '2022-03-02 20:58:24'),
(1, 7, '2022-03-02 20:58:24'),
(2, 1, '2022-02-02 20:58:24'),
(2, 3, '2022-03-02 20:58:24'),
(2, 4, '2022-03-02 20:58:24'),
(2, 5, '2022-03-02 20:58:24');

-- --------------------------------------------------------

--
-- Structure de la table `membre_groupe_efg`
--

DROP TABLE IF EXISTS `membre_groupe_efg`;
CREATE TABLE IF NOT EXISTS `membre_groupe_efg` (
  `id_personne` int(11) NOT NULL,
  `id_createur` int(11) NOT NULL,
  `id_efg` int(11) NOT NULL,
  PRIMARY KEY (`id_personne`,`id_createur`,`id_efg`),
  KEY `fk_personne_efg_personne2_idx` (`id_personne`),
  KEY `fk_membre_groupe_efg_groupe_efg1_idx` (`id_createur`,`id_efg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `membre_groupe_efg`
--

INSERT INTO `membre_groupe_efg` (`id_personne`, `id_createur`, `id_efg`) VALUES
(3, 3, 1),
(3, 3, 2),
(4, 3, 2),
(4, 4, 1),
(5, 3, 1),
(5, 5, 2),
(6, 3, 1),
(6, 5, 2),
(7, 4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(45) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `est_formateur` tinyint(4) NOT NULL DEFAULT '0',
  `est_gestionnaire` tinyint(4) NOT NULL DEFAULT '0',
  `est_administrateur` tinyint(4) NOT NULL DEFAULT '0',
  `url_photo` varchar(65) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `inscrit_a` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_personne`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id_personne`, `prenom`, `nom`, `email`, `pwd`, `tel`, `est_formateur`, `est_gestionnaire`, `est_administrateur`, `url_photo`, `token`, `inscrit_a`) VALUES
(1, 'Tryphon', 'Tournesol', 'formateur1@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0601020304', 1, 0, 0, 'formateur1@gmail.com.jpg', NULL, '2022-01-02 20:58:24'),
(2, 'Bianca', 'Castafiore', 'formateur2@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0611121314', 1, 0, 0, 'formateur2@gmail.com.jpg', NULL, '2022-01-02 20:58:24'),
(3, 'Manuel', 'Rivière', 'etudiant1@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0621222324', 0, 0, 0, 'etudiant1@gmail.com.jpg', NULL, '2022-02-02 20:58:24'),
(4, 'Marguerite', 'Moulin', 'etudiant2@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0631323334', 0, 0, 0, 'etudiant2@gmail.com.jpg', NULL, '2022-02-02 20:58:24'),
(5, 'Nadia', 'Lupin', 'etudiant3@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0631323334', 0, 0, 0, 'etudiant3@gmail.com.jpg', NULL, '2022-02-15 20:58:24'),
(6, 'Marguerite', 'Gatot', 'etudiant4@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0641424344', 0, 0, 0, 'etudiant4@gmail.com.jpg', NULL, '2022-02-15 20:58:24'),
(7, 'Karim', 'Malingua', 'etudiant5@gmail.com', '$2a$10$AxMO8nyOCdWAZhjXLuZqiut1s6PDgnVAWrLuuuRdSHO/Ye8A6YEIq', '0651525354', 0, 0, 0, 'etudiant5@gmail.com.jpg', NULL, '2022-02-15 20:58:24');

-- --------------------------------------------------------

--
-- Structure de la table `personne_role`
--

DROP TABLE IF EXISTS `personne_role`;
CREATE TABLE IF NOT EXISTS `personne_role` (
  `personnes_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`personnes_id`,`roles_id`),
  KEY `FKc6dfc5scokvhdj4by38b9ghvj` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne_role`
--

INSERT INTO `personne_role` (`personnes_id`, `roles_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `proposition`
--

DROP TABLE IF EXISTS `proposition`;
CREATE TABLE IF NOT EXISTS `proposition` (
  `id_proposition` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) NOT NULL,
  `libelle` varchar(128) NOT NULL,
  `est_correcte` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_proposition`),
  KEY `fk_option_question_question1_idx` (`id_question`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `proposition`
--

INSERT INTO `proposition` (`id_proposition`, `id_question`, `libelle`, `est_correcte`) VALUES
(1, 4, 'private', 0),
(2, 4, 'protege', 1),
(3, 4, 'public', 0),
(4, 5, '1', 0),
(5, 5, '01', 1),
(6, 5, 'erreur', 0),
(7, 6, 'kaki', 1),
(8, 6, 'melon', 0),
(9, 6, 'orange', 1),
(10, 7, 'chou', 1),
(11, 7, 'carotte', 0),
(12, 7, 'epinard', 0),
(13, 1, 'oui', 0),
(14, 1, 'non', 0),
(15, 2, '1/2h', NULL),
(16, 2, '1h', NULL),
(17, 2, '1h30', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(128) NOT NULL,
  `id_canal` int(11) NOT NULL,
  `id_createur` int(11) NOT NULL,
  `id_type_question` int(11) NOT NULL,
  `id_questionnaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_question`),
  KEY `fk_question_personne_idx` (`id_createur`),
  KEY `fk_question_canal_idx` (`id_canal`),
  KEY `fk_question_type_question_idx` (`id_type_question`),
  KEY `fk_question_questionnaire1_idx` (`id_questionnaire`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`id_question`, `libelle`, `id_canal`, `id_createur`, `id_type_question`, `id_questionnaire`) VALUES
(1, 'Avez-vous finiï¿½?', 3, 1, 1, NULL),
(2, 'Combien de temps voulez-vous pour ce TPï¿½?', 3, 1, 3, NULL),
(3, 'Donnez un exemple de classe abstraite', 1, 1, 2, NULL),
(4, 'Chassez l\'intrus', 1, 2, 3, 1),
(5, 'Que vaut s, avec String s = \"0\" + 1 ?', 1, 1, 3, 1),
(6, 'Quel fruit est un fruit d\'hiverï¿½?', 2, 1, 3, 2),
(7, 'Quel l�gume est le plus riche en vitamine C?', 2, 1, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE IF NOT EXISTS `questionnaire` (
  `id_questionnaire` int(11) NOT NULL AUTO_INCREMENT,
  `id_createur` int(11) NOT NULL,
  `libelle` varchar(45) NOT NULL,
  `id_canal` int(11) NOT NULL,
  `date_ajout` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_questionnaire`),
  KEY `fk_questionnaire_personne_idx` (`id_createur`),
  KEY `fk_questionnaire_canal1_idx` (`id_canal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Le questionnaire géré par un formateur => trigger';

--
-- Déchargement des données de la table `questionnaire`
--

INSERT INTO `questionnaire` (`id_questionnaire`, `id_createur`, `libelle`, `id_canal`, `date_ajout`) VALUES
(1, 1, 'Bases de Java', 1, '2022-04-02 20:58:24'),
(2, 1, 'Fruits et légumes', 1, '2022-04-02 20:58:24');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id_question` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `id_proposition` int(11) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  `date_rendu` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_question`,`id_personne`),
  KEY `fk_reponse_quesion_personne_idx` (`id_personne`),
  KEY `fk_reponse_question_question_idx` (`id_question`),
  KEY `fk_reponse_proposition1_idx` (`id_proposition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id_question`, `id_personne`, `id_proposition`, `libelle`, `date_rendu`) VALUES
(1, 3, NULL, 'Oui', '2022-04-02 20:58:24'),
(1, 4, NULL, 'Oui', '2022-04-02 20:58:24'),
(1, 5, NULL, 'Non', '2022-04-02 20:58:24'),
(1, 6, NULL, 'Oui', '2022-04-02 20:58:24'),
(1, 7, NULL, 'Non', '2022-04-02 20:58:24'),
(2, 3, NULL, '1h30', '2022-04-02 20:58:24'),
(2, 4, NULL, '1h', '2022-04-02 20:58:24'),
(2, 5, NULL, '1h30', '2022-04-02 20:58:24'),
(2, 6, NULL, '1h', '2022-04-02 20:58:24'),
(2, 7, NULL, '1h30', '2022-04-02 20:58:24'),
(3, 3, NULL, 'java.util.List', '2022-04-02 20:58:24'),
(3, 4, NULL, 'ArrayList', '2022-04-02 20:58:24'),
(3, 5, NULL, 'java.sql.Connection', '2022-04-02 20:58:24'),
(3, 6, NULL, 'je sais pas', '2022-04-02 20:58:24');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mxreaibdd57kib96h1is2qfkt` (`libelle`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `libelle`) VALUES
(2, 'ROLE_FORMATEUR'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `type_question`
--

DROP TABLE IF EXISTS `type_question`;
CREATE TABLE IF NOT EXISTS `type_question` (
  `id_type_question` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`id_type_question`),
  UNIQUE KEY `libelle_UNIQUE` (`libelle`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_question`
--

INSERT INTO `type_question` (`id_type_question`, `libelle`) VALUES
(3, 'options multiples'),
(1, 'oui/non'),
(2, 'rÃ©ponse libre');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `copie`
--
ALTER TABLE `copie`
  ADD CONSTRAINT `fk_questionnaire_personne_personne1` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_questionnaire_personne_questionnaire1` FOREIGN KEY (`id_questionnaire`) REFERENCES `questionnaire` (`id_questionnaire`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `efg`
--
ALTER TABLE `efg`
  ADD CONSTRAINT `fk_efg_membre_canal1` FOREIGN KEY (`id_canal`,`id_createur`) REFERENCES `membre_canal` (`id_canal`, `id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `groupe_efg`
--
ALTER TABLE `groupe_efg`
  ADD CONSTRAINT `fk_personne_efg_efg1` FOREIGN KEY (`id_efg`) REFERENCES `efg` (`id_efg`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_personne_efg_personne1` FOREIGN KEY (`id_createur`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `membre_canal`
--
ALTER TABLE `membre_canal`
  ADD CONSTRAINT `fk_membre_canal_canal` FOREIGN KEY (`id_canal`) REFERENCES `canal` (`id_canal`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_membre_canal_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `membre_groupe_efg`
--
ALTER TABLE `membre_groupe_efg`
  ADD CONSTRAINT `fk_membre_groupe_efg_groupe_efg1` FOREIGN KEY (`id_createur`,`id_efg`) REFERENCES `groupe_efg` (`id_createur`, `id_efg`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_personne_efg_personne2` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `personne_role`
--
ALTER TABLE `personne_role`
  ADD CONSTRAINT `FKc6dfc5scokvhdj4by38b9ghvj` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKl8tkm649pdk9iid3u1nmw0ky0` FOREIGN KEY (`personnes_id`) REFERENCES `personne` (`id_personne`);

--
-- Contraintes pour la table `proposition`
--
ALTER TABLE `proposition`
  ADD CONSTRAINT `fk_option_question_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_canal` FOREIGN KEY (`id_canal`) REFERENCES `canal` (`id_canal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_question_personne` FOREIGN KEY (`id_createur`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_question_questionnaire1` FOREIGN KEY (`id_questionnaire`) REFERENCES `questionnaire` (`id_questionnaire`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_question_type_question` FOREIGN KEY (`id_type_question`) REFERENCES `type_question` (`id_type_question`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `questionnaire`
--
ALTER TABLE `questionnaire`
  ADD CONSTRAINT `fk_questionnaire_canal1` FOREIGN KEY (`id_canal`) REFERENCES `canal` (`id_canal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_questionnaire_personne` FOREIGN KEY (`id_createur`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_reponse_proposition1` FOREIGN KEY (`id_proposition`) REFERENCES `proposition` (`id_proposition`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reponse_question_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reponse_question_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
