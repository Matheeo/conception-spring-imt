INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'John', 1);
INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'Jane', 2);
INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'Jack', 3);
INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'Jill', 4);
INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'Jim', 5);
INSERT INTO joueur (nom, prenom, numero) VALUES ('Doe', 'Jenny', 6);

INSERT INTO equipe (nom) VALUES ('Equipe 1');
INSERT INTO equipe (nom) VALUES ('Equipe 2');
INSERT INTO equipe (nom) VALUES ('Equipe 3');

INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (1, 1);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (1, 2);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (2, 3);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (2, 4);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (3, 5);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (3, 6);

INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 2, 21, 14, 1);
INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 2, 19, 21, 2);
INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 2, 21, 17, 3);
INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 2, 2, 50, 4);
INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (2, 3, 1, 2, 1);
INSERT INTO round (equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES  (2, 3, 2, 5, 2);

INSERT INTO `match` (equipea_id, equipeb_id, status) VALUES (1, 2, 2);
INSERT INTO `match` (equipea_id, equipeb_id, status) VALUES (2, 3, 2);

insert into match_rounds (match_id, rounds_id) VALUES (1, 1)
insert into match_rounds (match_id, rounds_id) VALUES (1, 2)
insert into match_rounds (match_id, rounds_id) VALUES (1, 3)
insert into match_rounds (match_id, rounds_id) VALUES (1, 4)
insert into match_rounds (match_id, rounds_id) VALUES (2, 5)
insert into match_rounds (match_id, rounds_id) VALUES (2, 6)

INSERT INTO resultat (match_id) VALUES (1);
INSERT INTO resultat (match_id) VALUES (2);

INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, est_administrateur) VALUES ('Baptiste', 'Bayche', 'baptisteLeSudiste@marseille.com', 'admin', true);
INSERT INTO utilisateur (nom, prenom, email, mot_de_passe,  est_administrateur) VALUES ('Mathéo', 'Vallée', 'matheovll35@gmail.com', 'admin', true);
INSERT INTO utilisateur (nom, prenom, email, mot_de_passe,  est_administrateur) VALUES ('Camille', 'Gouault--Lamour', 'camille@camille.com', 'admin', true);