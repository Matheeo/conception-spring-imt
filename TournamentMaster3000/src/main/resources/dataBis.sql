INSERT INTO joueur (id, nom, prenom, numero) VALUES (1, 'Doe', 'John', 1);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (2, 'Doe', 'Jane', 2);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (3, 'Doe', 'Jack', 3);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (4, 'Doe', 'Jill', 4);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (5, 'Doe', 'Jim', 5);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (6, 'Doe', 'Jenny', 6);

INSERT INTO equipe (id, nom) VALUES (1, 'Equipe 1');
INSERT INTO equipe (id, nom) VALUES (2, 'Equipe 2');

INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 1, 2, 21, 14, 1);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (2, 1, 2, 19, 21, 2);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (3, 1, 2, 21, 17, 3);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (4, 1, 2, 2, 1, 4);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (5, 1, 2, 1, 2, 5);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (6, 1, 2, 2, 1, 6);

INSERT INTO `match` (id, equipea_id, equipeb_id, status) VALUES (1, 1, 2, 2);
INSERT INTO `match` (id, equipea_id, equipeb_id, status) VALUES (2, 1, 2, 2);

INSERT INTO resultat (id, match_id) VALUES (1, 1);
INSERT INTO resultat (id, match_id) VALUES (2, 2);

INSERT INTO utilisateur (id, nom, prenom, email, mot_de_passe, est_administrateur) VALUES (1, 'Baptiste', 'Bayche', 'baptisteLeSudiste@marseille.com', 'admin', true);
INSERT INTO utilisateur (id, nom, prenom, email, mot_de_passe,  est_administrateur) VALUES (2, 'Mathéo', 'Vallée', 'matheovll35@gmail.com', 'admin', true);
INSERT INTO utilisateur (id, nom, prenom, email, mot_de_passe,  est_administrateur) VALUES (3, 'Camille', 'Gouault--Lamour', 'camille@camille.com', 'admin', true);