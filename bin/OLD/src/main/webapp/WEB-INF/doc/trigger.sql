DELIMITER $$
DROP TRIGGER IF EXISTS seance_before_insert_trigger $$
CREATE TRIGGER seance_before_insert_trigger 
BEFORE INSERT ON seance
FOR EACH ROW
BEGIN
	DECLARE var_est_formateur TINYINT;
	SELECT est_formateur INTO var_est_formateur
    FROM personne p WHERE p.id_personne = NEW.id_formateur;
    
    IF var_est_formateur = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='La personne indiquee formateur n\'est pas formateur',
        MYSQL_ERRNO=3000;
    END if;
END $$

INSERT INTO salle(est_virtuelle, nom) VALUES (0,'F400');

INSERT INTO seance(id_salle, id_module, id_session_formation, id_formateur) VALUES (1,1,1,1);