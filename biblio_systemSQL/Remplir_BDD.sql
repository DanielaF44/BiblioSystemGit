
--
-- TOC entry 3447 (class 0 OID 24702)
-- Dependencies: 220
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'USER');
INSERT INTO public.role (id, name) VALUES (2, 'ADMIN');



--
-- TOC entry 3439 (class 0 OID 16440)
-- Dependencies: 212
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auteur (id, nom, prenom) VALUES (1, 'Rowling', 'JK');
INSERT INTO public.auteur (id, nom, prenom) VALUES (2, 'Love Peacock', 'Thomas');
INSERT INTO public.auteur (id, nom, prenom) VALUES (3, 'Nietzsche', 'Friedrich');
INSERT INTO public.auteur (id, nom, prenom) VALUES (4, 'Zola', 'Emile');


--
-- TOC entry 3442 (class 0 OID 16470)
-- Dependencies: 215
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bibliotheque (nom) VALUES ('Biblio1');
INSERT INTO public.bibliotheque (nom) VALUES ('Biblio2');


--
-- TOC entry 3441 (class 0 OID 16460)
-- Dependencies: 214
-- Data for Name: editeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.editeur (id, nom) VALUES (1, 'Hachette');
INSERT INTO public.editeur (id, nom) VALUES (2, 'Editis');
INSERT INTO public.editeur (id, nom) VALUES (3, 'Flammarion');



--
-- TOC entry 3446 (class 0 OID 16510)
-- Dependencies: 219
-- Data for Name: langue; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.langue (nom) VALUES ('Français');
INSERT INTO public.langue (nom) VALUES ('Anglais');


--
-- TOC entry 3436 (class 0 OID 16401)
-- Dependencies: 209
-- Data for Name: livres; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livres (titre, date_publication, isbn, description, editeur_id, langue, cover) VALUES ('Harry Potter la chambre des secrets', '2001-04-04', '9788869185182', 'Harry Potter fait une rentrée fracassante en voiture volante. Cette deuxième année ne sera pas de tout repos... surtout depuis qu''une étrange malédiction plane sur les élèves. Entre les cours de potion magique, les matches de Quidditch et les combats de mauvais sorts, Harry trouvera-t-il le temps de percer le mystère de la Chambre des Secrets? Un livre magique pour sorciers confirmés.', 1, 'Français', 'harry.png');
INSERT INTO public.livres (titre, date_publication, isbn, description, editeur_id, langue, cover) VALUES ('Robin Hood', '2004-09-10', '9781781665299', 'Nunc egestas rutrum mauris, sit amet tempus diam. Pellentesque vel purus sit amet tortor pharetra rhoncus sit amet at mi. Vestibulum ac laoreet tortor, quis sagittis turpis. Nunc nec finibus lorem. Suspendisse mollis pretium dapibus.', 2, 'Anglais', 'robin-hood-97.jpg');
INSERT INTO public.livres (titre, date_publication, isbn, description, editeur_id, langue, cover) VALUES ('Crépuscule des idoles', '2021-05-09', '9798748051408', 'Il y a dans le monde plus d''idoles que de réalités : c''est ce que m''append le mauvais oeil que je jette sur le monde, et aussi la méchante oreille que je lui prête... Ce petit livre est une grande déclaration de guerre.', 3, 'Français', 'crepuscule_des_idoles.jpg');
INSERT INTO public.livres (titre, date_publication, isbn, description, editeur_id, langue, cover) VALUES ('Germinal', '2018-09-26', '9782266289191', 'Pour suivre le destin d''Etienne Lantier, Zola visite les bassins houillers, descend dans les puits, étudie Marx et Proudhon, s''informe sur les luttes prolétariennes.', 3, 'Français', 'germinal.jpg');


--
-- TOC entry 3443 (class 0 OID 16475)
-- Dependencies: 216
-- Data for Name: exemplaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (1, '9788869185182', 'Biblio1', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (2, '9788869185182', 'Biblio1', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (3, '9781781665299', 'Biblio1', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (4, '9781781665299', 'Biblio2', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (5, '9798748051408', 'Biblio1', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (6, '9798748051408', 'Biblio2', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (7, '9782266289191', 'Biblio1', NULL);
INSERT INTO public.exemplaire (id, isbn, bibliotheque, pret_id) VALUES (8, '9782266289191', 'Biblio2', NULL);


--
-- TOC entry 3445 (class 0 OID 16495)
-- Dependencies: 218
-- Data for Name: pret; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret (id, date_debut, date_fin, renouvele, utilisateur_id, exemplaire_id) VALUES (1, '2022-10-01', '2022-10-16', false, 10, 1);
INSERT INTO public.pret (id, date_debut, date_fin, renouvele, utilisateur_id, exemplaire_id) VALUES (2, '2022-10-02', '2022-10-25', true, 10, 2);
INSERT INTO public.pret (id, date_debut, date_fin, renouvele, utilisateur_id, exemplaire_id) VALUES (3, '2022-10-03', '2022-10-25', true, 10, 3);



--
-- TOC entry 3437 (class 0 OID 16420)
-- Dependencies: 210
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.genre (nom) VALUES ('Roman');
INSERT INTO public.genre (nom) VALUES ('Littérature');
INSERT INTO public.genre (nom) VALUES ('Historique');
INSERT INTO public.genre (nom) VALUES ('Philosophie');


--
-- TOC entry 3440 (class 0 OID 16445)
-- Dependencies: 213
-- Data for Name: livre_auteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre_auteur (isbn, auteur_id) VALUES ('9788869185182', 1);
INSERT INTO public.livre_auteur (isbn, auteur_id) VALUES ('9781781665299', 2);
INSERT INTO public.livre_auteur (isbn, auteur_id) VALUES ('9798748051408', 3);
INSERT INTO public.livre_auteur (isbn, auteur_id) VALUES ('9782266289191', 4);


--
-- TOC entry 3438 (class 0 OID 16425)
-- Dependencies: 211
-- Data for Name: livre_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre_genre (isbn, nom) VALUES ('9788869185182', 'Roman');
INSERT INTO public.livre_genre (isbn, nom) VALUES ('9781781665299', 'Historique');
INSERT INTO public.livre_genre (isbn, nom) VALUES ('9798748051408', 'Philosophie');
INSERT INTO public.livre_genre (isbn, nom) VALUES ('9782266289191', 'Littérature');





