--
-- iqtkcvfcyvyuabQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2022-10-15 10:00:53


ALTER TABLE ONLY public.pret DROP CONSTRAINT utilisateur_id;
ALTER TABLE ONLY public.utilisateur_role DROP CONSTRAINT user_id;
ALTER TABLE ONLY public.batch_step_execution_context DROP CONSTRAINT step_exec_ctx_fk;
ALTER TABLE ONLY public.utilisateur_role DROP CONSTRAINT role_id;
ALTER TABLE ONLY public.exemplaire DROP CONSTRAINT pret_id;
ALTER TABLE ONLY public.livre_genre DROP CONSTRAINT nom;
ALTER TABLE ONLY public.livres DROP CONSTRAINT langue;
ALTER TABLE ONLY public.batch_job_execution DROP CONSTRAINT job_inst_exec_fk;
ALTER TABLE ONLY public.batch_step_execution DROP CONSTRAINT job_exec_step_fk;
ALTER TABLE ONLY public.batch_job_execution_params DROP CONSTRAINT job_exec_params_fk;
ALTER TABLE ONLY public.batch_job_execution_context DROP CONSTRAINT job_exec_ctx_fk;
ALTER TABLE ONLY public.exemplaire DROP CONSTRAINT isbn;
ALTER TABLE ONLY public.livre_auteur DROP CONSTRAINT isbn;
ALTER TABLE ONLY public.livre_genre DROP CONSTRAINT isbn;
ALTER TABLE ONLY public.pret DROP CONSTRAINT exemplaire_id;
ALTER TABLE ONLY public.livres DROP CONSTRAINT editeur_id;
ALTER TABLE ONLY public.exemplaire DROP CONSTRAINT bibliotheque;
ALTER TABLE ONLY public.livre_auteur DROP CONSTRAINT auteur_id;
ALTER TABLE ONLY public.utilisateur_role DROP CONSTRAINT utilisateur_role_pkey;
ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT utilisateur_pkey;
ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT ukrma38wvnqfaf66vvmi57c71lo;
ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY public.pret DROP CONSTRAINT pret_pkey;
ALTER TABLE ONLY public.livres DROP CONSTRAINT livres_pkey;
ALTER TABLE ONLY public.livre_genre DROP CONSTRAINT livre_genre_pkey;
ALTER TABLE ONLY public.livre_auteur DROP CONSTRAINT livre_auteur_pkey;
ALTER TABLE ONLY public.langue DROP CONSTRAINT langue_pkey;
ALTER TABLE ONLY public.batch_job_instance DROP CONSTRAINT job_inst_un;
ALTER TABLE ONLY public.genre DROP CONSTRAINT genre_pkey;
ALTER TABLE ONLY public.exemplaire DROP CONSTRAINT exemplaire_pkey;
ALTER TABLE ONLY public.editeur DROP CONSTRAINT editeur_pkey;
ALTER TABLE ONLY public.bibliotheque DROP CONSTRAINT "bibliothèque_pkey";
ALTER TABLE ONLY public.batch_step_execution DROP CONSTRAINT batch_step_execution_pkey;
ALTER TABLE ONLY public.batch_step_execution_context DROP CONSTRAINT batch_step_execution_context_pkey;
ALTER TABLE ONLY public.batch_job_instance DROP CONSTRAINT batch_job_instance_pkey;
ALTER TABLE ONLY public.batch_job_execution DROP CONSTRAINT batch_job_execution_pkey;
ALTER TABLE ONLY public.batch_job_execution_context DROP CONSTRAINT batch_job_execution_context_pkey;
ALTER TABLE ONLY public.auteur DROP CONSTRAINT auteur_pkey;
DROP TABLE public.utilisateur_role;
DROP SEQUENCE public.user_id_seq;
DROP TABLE public.utilisateur;
DROP TABLE public.role;
DROP TABLE public.pret;
DROP TABLE public.livres;
DROP TABLE public.livre_genre;
DROP TABLE public.livre_auteur;
DROP TABLE public.langue;
DROP TABLE public.genre;
DROP TABLE public.exemplaire;
DROP TABLE public.editeur;
DROP TABLE public.bibliotheque;
DROP SEQUENCE public.batch_step_execution_seq;
DROP TABLE public.batch_step_execution_context;
DROP TABLE public.batch_step_execution;
DROP SEQUENCE public.batch_job_seq;
DROP TABLE public.batch_job_instance;
DROP SEQUENCE public.batch_job_execution_seq;
DROP TABLE public.batch_job_execution_params;
DROP TABLE public.batch_job_execution_context;
DROP TABLE public.batch_job_execution;
DROP TABLE public.auteur;
DROP SCHEMA public;
--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: iqtkcvfcyvyuab
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO iqtkcvfcyvyuab;

GRANT ALL ON SCHEMA public TO iqtkcvfcyvyuab;
GRANT ALL ON SCHEMA public TO public;

--
-- TOC entry 212 (class 1259 OID 16440)
-- Name: auteur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.auteur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100)
);


ALTER TABLE public.auteur OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 224 (class 1259 OID 24737)
-- Name: batch_job_execution; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_job_execution (
    job_execution_id bigint NOT NULL,
    version bigint,
    job_instance_id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    status character varying(10),
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone,
    job_configuration_location character varying(2500)
);


ALTER TABLE public.batch_job_execution OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 228 (class 1259 OID 24781)
-- Name: batch_job_execution_context; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_job_execution_context OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 225 (class 1259 OID 24749)
-- Name: batch_job_execution_params; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_job_execution_params (
    job_execution_id bigint NOT NULL,
    type_cd character varying(6) NOT NULL,
    key_name character varying(100) NOT NULL,
    string_val character varying(250),
    date_val timestamp without time zone,
    long_val bigint,
    double_val double precision,
    identifying character(1) NOT NULL
);


ALTER TABLE public.batch_job_execution_params OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 230 (class 1259 OID 24794)
-- Name: batch_job_execution_seq; Type: SEQUENCE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE SEQUENCE public.batch_job_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_job_execution_seq OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 223 (class 1259 OID 24730)
-- Name: batch_job_instance; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_job_instance (
    job_instance_id bigint NOT NULL,
    version bigint,
    job_name character varying(100) NOT NULL,
    job_key character varying(32) NOT NULL
);


ALTER TABLE public.batch_job_instance OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 231 (class 1259 OID 24795)
-- Name: batch_job_seq; Type: SEQUENCE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE SEQUENCE public.batch_job_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_job_seq OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 226 (class 1259 OID 24757)
-- Name: batch_step_execution; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_step_execution (
    step_execution_id bigint NOT NULL,
    version bigint NOT NULL,
    step_name character varying(100) NOT NULL,
    job_execution_id bigint NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone,
    status character varying(10),
    commit_count bigint,
    read_count bigint,
    filter_count bigint,
    write_count bigint,
    read_skip_count bigint,
    write_skip_count bigint,
    process_skip_count bigint,
    rollback_count bigint,
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone
);


ALTER TABLE public.batch_step_execution OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 227 (class 1259 OID 24769)
-- Name: batch_step_execution_context; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_step_execution_context OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 229 (class 1259 OID 24793)
-- Name: batch_step_execution_seq; Type: SEQUENCE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE SEQUENCE public.batch_step_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_step_execution_seq OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 215 (class 1259 OID 16470)
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.bibliotheque (
    nom character varying(50) NOT NULL
);


ALTER TABLE public.bibliotheque OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 214 (class 1259 OID 16460)
-- Name: editeur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.editeur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.editeur OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 216 (class 1259 OID 16475)
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.exemplaire (
    id integer NOT NULL,
    isbn character varying(20) NOT NULL,
    bibliotheque character varying(50),
    pret_id integer
);


ALTER TABLE public.exemplaire OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 210 (class 1259 OID 16420)
-- Name: genre; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.genre (
    nom character varying(20) NOT NULL
);


ALTER TABLE public.genre OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 219 (class 1259 OID 16510)
-- Name: langue; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.langue (
    nom character varying(50) NOT NULL
);


ALTER TABLE public.langue OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 213 (class 1259 OID 16445)
-- Name: livre_auteur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.livre_auteur (
    isbn character varying(20) NOT NULL,
    auteur_id integer NOT NULL
);


ALTER TABLE public.livre_auteur OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 211 (class 1259 OID 16425)
-- Name: livre_genre; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.livre_genre (
    isbn character varying(20) NOT NULL,
    nom character varying(20) NOT NULL
);


ALTER TABLE public.livre_genre OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 209 (class 1259 OID 16401)
-- Name: livres; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.livres (
    titre character varying(100) NOT NULL,
    date_publication date NOT NULL,
    isbn character varying NOT NULL,
    description character varying NOT NULL,
    editeur_id integer NOT NULL,
    langue character varying(50) NOT NULL,
    cover character varying
);


ALTER TABLE public.livres OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 218 (class 1259 OID 16495)
-- Name: pret; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.pret (
    id integer NOT NULL,
    date_debut date NOT NULL,
    date_fin date,
    renouvele boolean NOT NULL,
    utilisateur_id integer NOT NULL,
    exemplaire_id integer
);


ALTER TABLE public.pret OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 220 (class 1259 OID 24702)
-- Name: role; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.role OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 217 (class 1259 OID 16490)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100),
    mdp character varying(100) NOT NULL
);


ALTER TABLE public.utilisateur OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 222 (class 1259 OID 24729)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE SEQUENCE public.user_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 222
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER SEQUENCE public.user_id_seq OWNED BY public.utilisateur.id;


--
-- TOC entry 221 (class 1259 OID 24709)
-- Name: utilisateur_role; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.utilisateur_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.utilisateur_role OWNER TO iqtkcvfcyvyuab;

--
-- TOC entry 3246 (class 2606 OID 16444)
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- TOC entry 3278 (class 2606 OID 24787)
-- Name: batch_job_execution_context batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 3272 (class 2606 OID 24743)
-- Name: batch_job_execution batch_job_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- TOC entry 3268 (class 2606 OID 24734)
-- Name: batch_job_instance batch_job_instance_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- TOC entry 3276 (class 2606 OID 24775)
-- Name: batch_step_execution_context batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 3274 (class 2606 OID 24763)
-- Name: batch_step_execution batch_step_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- TOC entry 3252 (class 2606 OID 16474)
-- Name: bibliotheque bibliothèque_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT "bibliothèque_pkey" PRIMARY KEY (nom);


--
-- TOC entry 3250 (class 2606 OID 16464)
-- Name: editeur editeur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.editeur
    ADD CONSTRAINT editeur_pkey PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 16479)
-- Name: exemplaire exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 16424)
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (nom);


--
-- TOC entry 3270 (class 2606 OID 24736)
-- Name: batch_job_instance job_inst_un; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- TOC entry 3262 (class 2606 OID 16514)
-- Name: langue langue_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.langue
    ADD CONSTRAINT langue_pkey PRIMARY KEY (nom);


--
-- TOC entry 3248 (class 2606 OID 16449)
-- Name: livre_auteur livre_auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT livre_auteur_pkey PRIMARY KEY (isbn, auteur_id);


--
-- TOC entry 3244 (class 2606 OID 16429)
-- Name: livre_genre livre_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT livre_genre_pkey PRIMARY KEY (isbn, nom);


--
-- TOC entry 3240 (class 2606 OID 16413)
-- Name: livres livres_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (isbn);


--
-- TOC entry 3260 (class 2606 OID 16499)
-- Name: pret pret_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT pret_pkey PRIMARY KEY (id);


--
-- TOC entry 3264 (class 2606 OID 24708)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 32997)
-- Name: utilisateur ukrma38wvnqfaf66vvmi57c71lo; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT ukrma38wvnqfaf66vvmi57c71lo UNIQUE (email);


--
-- TOC entry 3258 (class 2606 OID 16494)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 3266 (class 2606 OID 24713)
-- Name: utilisateur_role utilisateur_role_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT utilisateur_role_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 3284 (class 2606 OID 16455)
-- Name: livre_auteur auteur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT auteur_id FOREIGN KEY (auteur_id) REFERENCES public.auteur(id);


--
-- TOC entry 3286 (class 2606 OID 16485)
-- Name: exemplaire bibliotheque; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT bibliotheque FOREIGN KEY (bibliotheque) REFERENCES public.bibliotheque(nom);


--
-- TOC entry 3279 (class 2606 OID 16465)
-- Name: livres editeur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT editeur_id FOREIGN KEY (editeur_id) REFERENCES public.editeur(id) NOT VALID;


--
-- TOC entry 3289 (class 2606 OID 16505)
-- Name: pret exemplaire_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT exemplaire_id FOREIGN KEY (exemplaire_id) REFERENCES public.exemplaire(id);


--
-- TOC entry 3281 (class 2606 OID 16430)
-- Name: livre_genre isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3283 (class 2606 OID 16450)
-- Name: livre_auteur isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3285 (class 2606 OID 16480)
-- Name: exemplaire isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- TOC entry 3296 (class 2606 OID 24788)
-- Name: batch_job_execution_context job_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3293 (class 2606 OID 24752)
-- Name: batch_job_execution_params job_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_params
    ADD CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3294 (class 2606 OID 24764)
-- Name: batch_step_execution job_exec_step_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- TOC entry 3292 (class 2606 OID 24744)
-- Name: batch_job_execution job_inst_exec_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES public.batch_job_instance(job_instance_id);


--
-- TOC entry 3280 (class 2606 OID 16533)
-- Name: livres langue; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT langue FOREIGN KEY (langue) REFERENCES public.langue(nom) NOT VALID;


--
-- TOC entry 3282 (class 2606 OID 16435)
-- Name: livre_genre nom; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT nom FOREIGN KEY (nom) REFERENCES public.genre(nom);


--
-- TOC entry 3287 (class 2606 OID 24724)
-- Name: exemplaire pret_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT pret_id FOREIGN KEY (pret_id) REFERENCES public.pret(id) NOT VALID;


--
-- TOC entry 3291 (class 2606 OID 24719)
-- Name: utilisateur_role role_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES public.role(id) NOT VALID;


--
-- TOC entry 3295 (class 2606 OID 24776)
-- Name: batch_step_execution_context step_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES public.batch_step_execution(step_execution_id);


--
-- TOC entry 3290 (class 2606 OID 24714)
-- Name: utilisateur_role user_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.utilisateur(id) NOT VALID;


--
-- TOC entry 3288 (class 2606 OID 16500)
-- Name: pret utilisateur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT utilisateur_id FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


-- Completed on 2022-10-15 10:00:53

--
-- iqtkcvfcyvyuabQL database dump complete
--

