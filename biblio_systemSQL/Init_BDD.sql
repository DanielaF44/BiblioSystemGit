--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: iqtkcvfcyvyuab
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO iqtkcvfcyvyuab;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: auteur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.auteur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100)
);


ALTER TABLE public.auteur OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: batch_job_execution_context; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_job_execution_context OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: batch_step_execution_context; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE public.batch_step_execution_context OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.bibliotheque (
    nom character varying(50) NOT NULL
);


ALTER TABLE public.bibliotheque OWNER TO iqtkcvfcyvyuab;

--
-- Name: editeur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.editeur (
    id integer NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.editeur OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: genre; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.genre (
    nom character varying(20) NOT NULL
);


ALTER TABLE public.genre OWNER TO iqtkcvfcyvyuab;

--
-- Name: langue; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.langue (
    nom character varying(50) NOT NULL
);


ALTER TABLE public.langue OWNER TO iqtkcvfcyvyuab;

--
-- Name: livre_auteur; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.livre_auteur (
    isbn character varying(20) NOT NULL,
    auteur_id integer NOT NULL
);


ALTER TABLE public.livre_auteur OWNER TO iqtkcvfcyvyuab;

--
-- Name: livre_genre; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.livre_genre (
    isbn character varying(20) NOT NULL,
    nom character varying(20) NOT NULL
);


ALTER TABLE public.livre_genre OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: role; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.role OWNER TO iqtkcvfcyvyuab;

--
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
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER SEQUENCE public.user_id_seq OWNED BY public.utilisateur.id;


--
-- Name: utilisateur_role; Type: TABLE; Schema: public; Owner: iqtkcvfcyvyuab
--

CREATE TABLE public.utilisateur_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.utilisateur_role OWNER TO iqtkcvfcyvyuab;

--
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- Name: batch_job_execution_context batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- Name: batch_job_execution batch_job_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- Name: batch_job_instance batch_job_instance_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- Name: batch_step_execution_context batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- Name: batch_step_execution batch_step_execution_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- Name: bibliotheque bibliothèque_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT "bibliothèque_pkey" PRIMARY KEY (nom);


--
-- Name: editeur editeur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.editeur
    ADD CONSTRAINT editeur_pkey PRIMARY KEY (id);


--
-- Name: exemplaire exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (nom);


--
-- Name: batch_job_instance job_inst_un; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- Name: langue langue_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.langue
    ADD CONSTRAINT langue_pkey PRIMARY KEY (nom);


--
-- Name: livre_auteur livre_auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT livre_auteur_pkey PRIMARY KEY (isbn, auteur_id);


--
-- Name: livre_genre livre_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT livre_genre_pkey PRIMARY KEY (isbn, nom);


--
-- Name: livres livres_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (isbn);


--
-- Name: pret pret_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT pret_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: utilisateur ukrma38wvnqfaf66vvmi57c71lo; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT ukrma38wvnqfaf66vvmi57c71lo UNIQUE (email);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- Name: utilisateur_role utilisateur_role_pkey; Type: CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT utilisateur_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: livre_auteur auteur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT auteur_id FOREIGN KEY (auteur_id) REFERENCES public.auteur(id);


--
-- Name: exemplaire bibliotheque; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT bibliotheque FOREIGN KEY (bibliotheque) REFERENCES public.bibliotheque(nom);


--
-- Name: livres editeur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT editeur_id FOREIGN KEY (editeur_id) REFERENCES public.editeur(id) NOT VALID;


--
-- Name: pret exemplaire_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT exemplaire_id FOREIGN KEY (exemplaire_id) REFERENCES public.exemplaire(id);


--
-- Name: livre_genre isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- Name: livre_auteur isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_auteur
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- Name: exemplaire isbn; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT isbn FOREIGN KEY (isbn) REFERENCES public.livres(isbn);


--
-- Name: batch_job_execution_context job_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_context
    ADD CONSTRAINT job_exec_ctx_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- Name: batch_job_execution_params job_exec_params_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution_params
    ADD CONSTRAINT job_exec_params_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- Name: batch_step_execution job_exec_step_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution
    ADD CONSTRAINT job_exec_step_fk FOREIGN KEY (job_execution_id) REFERENCES public.batch_job_execution(job_execution_id);


--
-- Name: batch_job_execution job_inst_exec_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES public.batch_job_instance(job_instance_id);


--
-- Name: livres langue; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT langue FOREIGN KEY (langue) REFERENCES public.langue(nom) NOT VALID;


--
-- Name: livre_genre nom; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.livre_genre
    ADD CONSTRAINT nom FOREIGN KEY (nom) REFERENCES public.genre(nom);


--
-- Name: exemplaire pret_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT pret_id FOREIGN KEY (pret_id) REFERENCES public.pret(id) NOT VALID;


--
-- Name: utilisateur_role role_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES public.role(id) NOT VALID;


--
-- Name: batch_step_execution_context step_exec_ctx_fk; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.batch_step_execution_context
    ADD CONSTRAINT step_exec_ctx_fk FOREIGN KEY (step_execution_id) REFERENCES public.batch_step_execution(step_execution_id);


--
-- Name: utilisateur_role user_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.utilisateur(id) NOT VALID;


--
-- Name: pret utilisateur_id; Type: FK CONSTRAINT; Schema: public; Owner: iqtkcvfcyvyuab
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT utilisateur_id FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: iqtkcvfcyvyuab
--

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO iqtkcvfcyvyuab;


--
-- PostgreSQL database dump complete
--

