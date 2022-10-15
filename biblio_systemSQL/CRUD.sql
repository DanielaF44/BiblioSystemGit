--script for crud on every TABLE

-----------------------------------
DELETE FROM public.auteur
	WHERE <condition>;

INSERT INTO public.auteur(
	id, nom, prenom)
	VALUES (?, ?, ?);

SELECT id, nom, prenom
	FROM public.auteur;

UPDATE public.auteur
	SET id=?, nom=?, prenom=?
	WHERE <condition>;
	
------------------------------------	
DELETE FROM public.bibliotheque
	WHERE <condition>;
	
INSERT INTO public.bibliotheque(
	nom)
	VALUES (?);

SELECT nom
	FROM public.bibliotheque;
	
UPDATE public.bibliotheque
	SET nom=?
	WHERE <condition>;
-------------------------------------

DELETE FROM public.editeur
	WHERE <condition>;

INSERT INTO public.editeur(
	id, nom)
	VALUES (?, ?);
	
SELECT id, nom
	FROM public.editeur;

UPDATE public.editeur
	SET id=?, nom=?
	WHERE <condition>;
	
------------------------------------

DELETE FROM public.exemplaire
	WHERE <condition>;
	
INSERT INTO public.exemplaire(
	id, isbn)
	VALUES (?, ?);
	
SELECT id, isbn
	FROM public.exemplaire;

UPDATE public.exemplaire
	SET id=?, isbn=?
	WHERE <condition>;
	
------------------------------------

DELETE FROM public.genre
	WHERE <condition>;

INSERT INTO public.genre(
	nom)
	VALUES (?);

SELECT nom
	FROM public.genre;
	
UPDATE public.genre
	SET nom=?
	WHERE <condition>;
------------------------------------

DELETE FROM public.langue
	WHERE <condition>;
	
INSERT INTO public.langue(
	nom)
	VALUES (?);
	
SELECT nom
	FROM public.langue;

UPDATE public.langue
	SET nom=?
	WHERE <condition>;

------------------------------------
DELETE FROM public.livre_auteur
	WHERE <condition>;

INSERT INTO public.livre_auteur(
	isbn, auteur_id)
	VALUES (?, ?);

SELECT isbn, auteur_id
	FROM public.livre_auteur;

UPDATE public.livre_auteur
	SET isbn=?, auteur_id=?
	WHERE <condition>;

-------------------------------------

DELETE FROM public.livre_genre
	WHERE <condition>;

INSERT INTO public.livre_genre(
	isbn, nom)
	VALUES (?, ?);

SELECT isbn, nom
	FROM public.livre_genre;
	
UPDATE public.livre_genre
	SET isbn=?, nom=?
	WHERE <condition>;
--------------------------------------
DELETE FROM public.livres
	WHERE <condition>;

INSERT INTO public.livres(
	isbn, cover, date_publication, description, titre, editeur_id, langue)
	VALUES (?, ?, ?, ?, ?, ?, ?);

SELECT isbn, cover, date_publication, description, titre, editeur_id, langue
	FROM public.livres;

UPDATE public.livres
	SET isbn=?, cover=?, date_publication=?, description=?, titre=?, editeur_id=?, langue=?
	WHERE <condition>;

--------------------------------------
DELETE FROM public.pret
	WHERE <condition>;

INSERT INTO public.pret(
	id, date_debut, date_fin, renouvele, exemplaire_id, utilisateur_id)
	VALUES (?, ?, ?, ?, ?, ?);

SELECT id, date_debut, date_fin, renouvele, exemplaire_id, utilisateur_id
	FROM public.pret;

UPDATE public.pret
	SET id=?, date_debut=?, date_fin=?, renouvele=?, exemplaire_id=?, utilisateur_id=?
	WHERE <condition>;

---------------------------------------

DELETE FROM public.role
	WHERE <condition>;

INSERT INTO public.role(
	id, name)
	VALUES (?, ?);
	
SELECT id, name
	FROM public.role;
	
UPDATE public.role
	SET id=?, name=?
	WHERE <condition>;	
	
---------------------------------------



