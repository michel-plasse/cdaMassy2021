/* QUESTION DAO SQL STATEMENT DOCUMENTATION

/* SELECT_BY_QUESTION_ID */
SELECT  q.*, p.nom, p.prenom
FROM question q
	INNER JOIN 
		personne p 
			ON p.id_personne = q.id_createur
WHERE id_question = 2;

/*SELECT_ALL_BY_CREATOR*/
SELECT q.*, p.prenom, p.nom
FROM question q
	INNER JOIN
		personne p
			ON q.id_createur = 1
LIMIT 2, 4;



/*SELECT_ALL_QUESTIONS_IN_LIMIT*/
SELECT q.*, p.prenom, p.nom
FROM question q
	INNER JOIN
		personne p
			ON q.id_createur = p.id_personne
LIMIT 2, 10;

/*SELECT_ALL_QUESTIONS_BY_CANAL*/
SELECT q.*, p.prenom, p.nom
FROM question q
	INNER JOIN
		personne p
			ON q.id_createur = p.id_personne
WHERE id_canal=1
LIMIT 0, 10;

/*SELECT_ALL_QUESTIONS_BY_QUESTIONNAIRE*/
SELECT q.*, p.prenom, p.nom
FROM question q
	INNER JOIN
		personne p
			ON q.id_createur = p.id_personne
WHERE id_questionnaire=1
LIMIT 0, 10;

/*SELECT_REPONSES_WITH_QUESTION_ID*/
SELECT r.*, p.prenom, p.nom
FROM reponse r
	INNER JOIN
		personne p
			ON r.id_personne = p.id_personne
WHERE id_question=1;

/*SELECT ALL PENDING QUESTION BY PERSONNE ID AND CANAL ID*/
SELECT q.*, p.prenom, p.nom
FROM question q
	INNER JOIN 
		personne p
			ON p.id_personne = q.id_createur
WHERE NOT EXISTS(
	SELECT* 
    FROM reponse r
    WHERE r.id_personne = 1
) AND id_canal = 1;