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
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: role_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_entity (
                                    id integer NOT NULL,
                                    name character varying(255)
);


ALTER TABLE public.role_entity OWNER TO postgres;

--
-- Name: user_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_entity (
                                    id integer NOT NULL,
                                    address character varying(255),
                                    name character varying(255),
                                    password character varying(255),
                                    phone character varying(255),
                                    surname character varying(255),
                                    username character varying(255),
                                    role_id integer
);


ALTER TABLE public.user_entity OWNER TO postgres;

--
-- Data for Name: role_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_entity (id, name) FROM stdin;
1       ROLE_ADMIN
2       ROLE_USER
\.


--
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_entity (id, address, name, password, phone, surname, username, role_id) FROM stdin;
4       admin   admin   $2a$12$pc.CTKzA/NCun7js4rHTquYr2gqom5POIhwktIIJEtzYDEpHoa9Ma    admin   admin   admin   1
5       user    user    $2a$12$svTL90A8w.o5RgHZMsR3eeeQO4vaEEDJuUpcO7r2crTFvCsmg.PMy    user    user    user    2
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 5, true);


--
-- Name: role_entity role_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_entity
    ADD CONSTRAINT role_entity_pkey PRIMARY KEY (id);


--
-- Name: user_entity user_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT user_entity_pkey PRIMARY KEY (id);


--
-- Name: user_entity fkc50fb2m5pqs8711tbas2jljlu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT fkc50fb2m5pqs8711tbas2jljlu FOREIGN KEY (role_id) REFERENCES public.role_entity(id);


--
-- PostgreSQL database dump complete
--
