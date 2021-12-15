CREATE DEFINER=`root`@`localhost` PROCEDURE `cdamassyResetBdd`()
BEGIN	
	-- desactiver les contraintes d'integrité
	SET foreign_key_checks = 0;
	TRUNCATE TABLE personne;
    TRUNCATE TABLE question;
    -- reactiver les contraintes d'integrité
    SET foreign_key_checks = 1;
    
    BEGIN

    INSERT INTO personne(id_personne,prenom,nom,email,pwd,tel,est_formateur,est_gestionnaire,est_administrateur)
    VALUES 
    (1,'David','Meurice','D.Meurice@gmail.com','aaa','0618192021',0,0,0),
	(2,'Thomas','Germain','T.Germaine@gmail.com','bbb','0622232425',0,0,0),
	(3,'Romain','Mae','R.Mae@gmail.com','ccc','0626272829',0,0,0),
    (4,'Marie','Dupont','M.Dupont@gmail.com','ddd','0630313233',0,0,0),
    (5,'Christophe','Genet','C.Genet@amail.com','eee','0634353637',0,0,0);
    
	INSERT INTO question(id,canal_id,auteur_id,enonce,type_reponses)
	VALUES 
    (1,null,1,'Ceci est la premiere question',1),
    (2,2,2,'Ceci est la deuxieme question',1),
    (3,3,3,'Ceci est la troisieme question',1),
    (4,4,3,'Ceci est la quatrieme question',1);
    END;
END