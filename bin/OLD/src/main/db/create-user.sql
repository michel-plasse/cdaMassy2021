use cdamassy2021;

CREATE USER cdamassy2021_user@localhost IDENTIFIED BY 'cdamassy2021_pwd';
GRANT ALL ON cdamassy2021.* TO cdamassy2021_user@localhost;
GRANT EXECUTE ON cdamassy2021.* TO cdamassy2021_user@localhost;
-- si l'instruction précédente échoue, lancer :
-- GRANT SELECT ON mysql.proc TO siomassy2021_user@localhost;
