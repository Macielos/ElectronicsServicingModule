CREATE DATABASE "ElectronicsServicePoint"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Polish_Poland.1250'
    LC_CTYPE = 'Polish_Poland.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE equipment_category
(
    id bigserial,
    name character varying NOT NULL,
    CONSTRAINT "equipment_category_pkey" PRIMARY KEY (id)
)

CREATE TABLE equipment
(
    id bigserial,
    name character varying(256) NOT NULL,
    category_id bigint NOT NULL,
    CONSTRAINT "equipment_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_cat_id FOREIGN KEY (category_id)
        REFERENCES public.equipment_category (id)
);

CREATE TABLE comment
(
    id bigserial,
    author character varying(256) NOT NULL,
    comment character varying(4096) NOT NULL,
    publication_date timestamp with time zone NOT NULL,
    equipment_id bigint NOT NULL,
    CONSTRAINT "comment_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_eq_id FOREIGN KEY (equipment_id)
        REFERENCES public.equipment (id)
);

CREATE TABLE equipment_parameter
(
    id bigserial,
    equipment_id bigint NOT NULL,
    name character varying(256),
    value character varying(256),
    CONSTRAINT "equipment_parameter_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_eq_id FOREIGN KEY (equipment_id)
        REFERENCES public.equipment (id)
);

CREATE TABLE issue
(
    id bigserial,
    title character varying(256) NOT NULL,
    description character varying(4096),
    creation_date timestamp with time zone NOT NULL,
    equipment_id bigint NOT NULL,
    status character varying(64) NOT NULL,
    CONSTRAINT "issue_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_eq_id FOREIGN KEY (equipment_id)
        REFERENCES public.equipment (id)
);

insert into equipment_category (name) values ('AGD'), ('RTV'), ('Computers'), ('Mobile');