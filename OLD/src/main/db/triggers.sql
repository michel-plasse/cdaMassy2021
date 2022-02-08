DELIMITER $$

-- Il faudrait le dupliquer en before update of id_createur
DROP TRIGGER IF EXISTS groupe_efg_before_insert$$
CREATE TRIGGER groupe_efg_before_insert
BEFORE INSERT ON groupe_efg
FOR EACH ROW
BEGIN
	DECLARE v_id_canal INT;
  DECLARE v_nb INT;
  DECLARE v_msg VARCHAR(55);
  -- Recuperer le id_canal de l'efg du groupe insere
  SELECT id_canal INTO v_id_canal
  FROM efg
  WHERE id_efg = NEW.id_efg;
  -- le createur est-il dans le canal de l'efg?
  SELECT COUNT(*) INTO v_nb
  FROM membre_canal
  WHERE id_canal = v_id_canal AND id_personne = NEW.id_createur;
  -- Pas trouvé ? => lancer une exception
  IF v_nb = 0 THEN
		SET v_msg = CONCAT('Créateur (', NEW.id_createur, ') d''un groupe de l''EFG ', 
			NEW.id_efg, ' pas dans le canal ', v_id_canal);
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT=v_msg, MYSQL_ERRNO=3000;
	END IF;
END$$

-- Ajoute le createur du groupe dans les membres du groupe
DROP TRIGGER IF EXISTS groupe_efg_after_insert$$
CREATE TRIGGER groupe_efg_after_insert
AFTER INSERT ON groupe_efg
FOR EACH ROW
BEGIN
	INSERT INTO membre_groupe_efg(id_personne, id_createur, id_efg)
  VALUES(NEW.id_createur, NEW.id_createur, NEW.id_efg)
  ON DUPLICATE KEY UPDATE id_personne = id_personne;
END$$

