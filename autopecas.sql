CREATE DATABASE autopecas
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('cliente_id_cliente_seq'::regclass),
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cliente
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.funcionario
(
    id_func integer NOT NULL DEFAULT nextval('funcionario_id_func_seq'::regclass),
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    login character varying(20) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(20) COLLATE pg_catalog."default" NOT NULL,
    gerente boolean NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (id_func)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.funcionario
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.peca
(
    id_peca integer NOT NULL DEFAULT nextval('peca_id_peca_seq'::regclass),
    nome character varying(20) COLLATE pg_catalog."default" NOT NULL,
    categoria character varying(20) COLLATE pg_catalog."default" NOT NULL,
    fabricante character varying(20) COLLATE pg_catalog."default" NOT NULL,
    preco double precision NOT NULL,
    quantidade_estoque integer NOT NULL,
    CONSTRAINT peca_pkey PRIMARY KEY (id_peca)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.peca
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.pedido
(
    id_pedido integer NOT NULL DEFAULT nextval('pedido_id_pedido_seq'::regclass),
    data_pedido date NOT NULL,
    status character varying(20) COLLATE pg_catalog."default" NOT NULL,
    id_func integer NOT NULL,
    id_cliente integer NOT NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido),
    CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT pedido_id_func_fkey FOREIGN KEY (id_func)
        REFERENCES public.funcionario (id_func) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pedido
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.nota_pedido
(
    id_nota integer NOT NULL DEFAULT nextval('nota_pedido_id_nota_seq'::regclass),
    id_peca integer NOT NULL,
    id_pedido integer NOT NULL,
    quantidade_peca integer NOT NULL,
    valor_total double precision NOT NULL,
    CONSTRAINT nota_pedido_pkey PRIMARY KEY (id_nota),
    CONSTRAINT nota_pedido_id_peca_fkey FOREIGN KEY (id_peca)
        REFERENCES public.peca (id_peca) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT nota_pedido_id_pedido_fkey FOREIGN KEY (id_pedido)
        REFERENCES public.pedido (id_pedido) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.nota_pedido
    OWNER to postgres;