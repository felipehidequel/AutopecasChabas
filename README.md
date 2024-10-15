## PRÉ REQUISISTO
* Java 23
* Maven
* PostgresSQL

## SCRIPT PARA CRIAÇÃO DO BANCO DE DADOS POSTGRES

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: autopecas; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE autopecas WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


ALTER DATABASE autopecas OWNER TO postgres;

\connect autopecas

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nome character varying(50) NOT NULL,
    telefone character varying(20) NOT NULL,
    cpf character varying(11) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id_func integer NOT NULL,
    nome character varying(50) NOT NULL,
    login character varying(20) NOT NULL,
    senha character varying(20) NOT NULL,
    gerente boolean NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: funcionario_id_func_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionario_id_func_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.funcionario_id_func_seq OWNER TO postgres;

--
-- Name: funcionario_id_func_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionario_id_func_seq OWNED BY public.funcionario.id_func;


--
-- Name: nota_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_pedido (
    id_nota integer NOT NULL,
    id_peca integer NOT NULL,
    id_pedido integer NOT NULL,
    quantidade_peca integer NOT NULL,
    valor_total double precision NOT NULL
);


ALTER TABLE public.nota_pedido OWNER TO postgres;

--
-- Name: nota_pedido_id_nota_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nota_pedido_id_nota_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.nota_pedido_id_nota_seq OWNER TO postgres;

--
-- Name: nota_pedido_id_nota_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nota_pedido_id_nota_seq OWNED BY public.nota_pedido.id_nota;


--
-- Name: peca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.peca (
    id_peca integer NOT NULL,
    nome character varying(20) NOT NULL,
    categoria character varying(20) NOT NULL,
    fabricante character varying(20) NOT NULL,
    preco double precision NOT NULL,
    quantidade_estoque integer NOT NULL
);


ALTER TABLE public.peca OWNER TO postgres;

--
-- Name: peca_id_peca_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.peca_id_peca_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.peca_id_peca_seq OWNER TO postgres;

--
-- Name: peca_id_peca_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.peca_id_peca_seq OWNED BY public.peca.id_peca;


--
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    id_pedido integer NOT NULL,
    data_pedido date NOT NULL,
    status character varying(20) NOT NULL,
    id_func integer NOT NULL,
    id_cliente integer NOT NULL
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- Name: pedido_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_id_pedido_seq OWNER TO postgres;

--
-- Name: pedido_id_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_id_pedido_seq OWNED BY public.pedido.id_pedido;


--
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: funcionario id_func; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id_func SET DEFAULT nextval('public.funcionario_id_func_seq'::regclass);


--
-- Name: nota_pedido id_nota; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_pedido ALTER COLUMN id_nota SET DEFAULT nextval('public.nota_pedido_id_nota_seq'::regclass);


--
-- Name: peca id_peca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.peca ALTER COLUMN id_peca SET DEFAULT nextval('public.peca_id_peca_seq'::regclass);


--
-- Name: pedido id_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id_pedido SET DEFAULT nextval('public.pedido_id_pedido_seq'::regclass);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, nome, telefone, cpf) FROM stdin;
114	Carlos Gabriel Ferreira	84996579714	08445687667
115	Roberto Fernandes Rocha	09887665434	11111111111
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id_func, nome, login, senha, gerente) FROM stdin;
92	ADMIN	admin123	admin123	t
93	FELIPE HIDEQUEL SANTOS DA SILVA	felipehidequel	As.alx.19	f
\.


--
-- Data for Name: nota_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nota_pedido (id_nota, id_peca, id_pedido, quantidade_peca, valor_total) FROM stdin;
25	106	55	20	1000
\.


--
-- Data for Name: peca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.peca (id_peca, nome, categoria, fabricante, preco, quantidade_estoque) FROM stdin;
106	PECA TESTE	GERAL	TESTE	50	0
\.


--
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedido (id_pedido, data_pedido, status, id_func, id_cliente) FROM stdin;
55	2024-10-15	COMPLETO	93	115
\.


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 115, true);


--
-- Name: funcionario_id_func_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_func_seq', 93, true);


--
-- Name: nota_pedido_id_nota_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nota_pedido_id_nota_seq', 25, true);


--
-- Name: peca_id_peca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.peca_id_peca_seq', 106, true);


--
-- Name: pedido_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_id_pedido_seq', 55, true);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id_func);


--
-- Name: nota_pedido nota_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_pedido
    ADD CONSTRAINT nota_pedido_pkey PRIMARY KEY (id_nota);


--
-- Name: peca peca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.peca
    ADD CONSTRAINT peca_pkey PRIMARY KEY (id_peca);


--
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido);


--
-- Name: nota_pedido nota_pedido_id_peca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_pedido
    ADD CONSTRAINT nota_pedido_id_peca_fkey FOREIGN KEY (id_peca) REFERENCES public.peca(id_peca) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nota_pedido nota_pedido_id_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_pedido
    ADD CONSTRAINT nota_pedido_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pedido pedido_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pedido pedido_id_func_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_id_func_fkey FOREIGN KEY (id_func) REFERENCES public.funcionario(id_func) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--
