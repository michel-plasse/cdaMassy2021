DELIMITER $$
DROP TRIGGER IF EXISTS produit_after_update_trigger $$
CREATE TRIGGER produit_after_update_trigger 
AFTER UPDATE ON produit
FOR EACH ROW
BEGIN

    IF  NEW.prix_actuel != OLD.prix_actuel THEN
        INSERT INTO tarif(date_effet, id_produit, prix)
        VALUES (NOW(), NEW.id_produit, NEW.prix_actuel);
    END if;

END $$


UPDATE produit
SET prix_actuel = 98
WHERE id_produit = 1;