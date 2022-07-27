--
-- PostgreSQL database dump
--

-CREATE DATABASE "BiblioSystem"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

--
-- TOC entry 212 (class 1259 OID 16440)
-- Name: auteur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auteur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100)
);


ALTER TABLE public.auteur OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16470)
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bibliotheque (
    nom character varying(50) NOT NULL
);


ALTER TABLE public.bibliotheque OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16460)
-- Name: editeur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editeur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.editeur OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16475)
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exemplaire (
    id integer NOT NULL,
    isbn character varying(20) NOT NULL,
    bibliotheque character varying(50) NOT NULL
);


ALTER TABLE public.exemplaire OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16420)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    nom character varying(20) NOT NULL
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16445)
-- Name: livre_auteur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre_auteur (
    isbn character varying(20) NOT NULL,
    auteur_id integer NOT NULL
);


ALTER TABLE public.livre_auteur OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16425)
-- Name: livre_genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre_genre (
    isbn character varying(20) NOT NULL,
    nom character varying(20) NOT NULL
);


ALTER TABLE public.livre_genre OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16401)
-- Name: livres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livres (
    titre character varying NOT NULL,
    date_publication date NOT NULL,
    isbn character varying NOT NULL,
    description character varying NOT NULL,
    editeur_id integer
);


ALTER TABLE public.livres OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16495)
-- Name: pret; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pret (
    id integer NOT NULL,
    date_debut date NOT NULL,
    date_fin date,
    renouvele boolean NOT NULL,
    utilisateur_id integer NOT NULL,
    exemplaire_id integer NOT NULL
);


ALTER TABLE public.pret OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16490)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100),
    mdp character varying(100) NOT NULL
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 3206 (class 2606 OID 16444)
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- TOC entry 3212 (class 2606 OID 16474)
-- Name: bibliotheque bibliothèque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT "bibliothèque_pkey" PRIMARY KEY (nom);


--
-- TOC entry 3210 (class 2606 OID 16464)
-- Name: editeur editeur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editeur
    ADD CONSTRAINT editeur_pkey PRIMARY KEY (id);


--
-- TOC entry 3214 (class 2606 OID 16479)
-- Name: exemplaire exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 16424)
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (nom);


--
-- TOC entry 3208 (class 2606 OID 16449)
-- Name: livre_auteur livre_auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT livre_auteur_pkey PRIMARY KEY (isbn, auteur_id);


--
-- TOC entry 3204 (class 2606 OID 16429)
-- Name: livre_genre livre_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT livre_genre_pkey PRIMARY KEY (isbn, nom);


--
-- TOC entry 3200 (class 2606 OID 16413)
-- Name: livres livres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (isbn);


--
-- TOC entry 3218 (class 2606 OID 16499)
-- Name: pret pret_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT pret_pkey PRIMARY KEY (id);


--
-- TOC entry 3216 (class 2606 OID 16494)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 3223 (class 2606 OID 16455)
-- Name: livre_auteur auteur_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT auteur_id FOREIGN KEY (auteur_id) REFERENCES public.auteur(id);


--
-- TOC entry 3225 (class 2606 OID 16485)
-- Name: exemplaire bibliotheque; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT bibliotheque FOREIGN KEY (bibliotheque) REFERENCES public.bibliotheque(nom);


--
-- TOC entry 3219 (class 2606 OID 16465)
-- Name: livres editeur_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT editeur_id FOREIGN KEY (editeur_id) REFERENCES public.editeur(id) NOT VALID;


--
-- TOC entry 3227 (class 2606 OID 16505)
-- Name: pret exemplaire_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT exemplaire_id FOREIGN KEY (exemplaire_id) REFERENCES public.exemplaire(id);


--
-- TOC entry 3220 (class 2606 OID 16430)
-- Name: livre_genre isbn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3222 (class 2606 OID 16450)
-- Name: livre_auteur isbn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3224 (class 2606 OID 16480)
-- Name: exemplaire isbn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3221 (class 2606 OID 16435)
-- Name: livre_genre nom; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT nom FOREIGN KEY (nom) REFERENCES public.genre(nom);


--
-- TOC entry 3226 (class 2606 OID 16500)
-- Name: pret utilisateur_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT utilisateur_id FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


-- Completed on 2022-07-27 11:08:48

--
-- PostgreSQL database dump complete
--

