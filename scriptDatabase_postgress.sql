-- Database: mtd_pos_fact_elect

-- DROP DATABASE mtd_pos_fact_elect;

CREATE DATABASE mtd_pos_fact_elect
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Ecuador.1252'
    LC_CTYPE = 'Spanish_Ecuador.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	CREATE SCHEMA mtd
    AUTHORIZATION postgres;
	
	-- Table: mtd.pos_cab_fact_electronica

-- DROP TABLE mtd.pos_cab_fact_electronica;

CREATE TABLE mtd.pos_cab_fact_electronica
(
    identificacion_empresa character varying(13) COLLATE pg_catalog."default" NOT NULL,
    establecimiento character varying(3) COLLATE pg_catalog."default" NOT NULL,
    punto_emision character varying(3) COLLATE pg_catalog."default" NOT NULL,
    secuencial character varying(9) COLLATE pg_catalog."default" NOT NULL,
    clave_acceso character varying(49) COLLATE pg_catalog."default" NOT NULL,
    razon_social_empresa character varying(100) COLLATE pg_catalog."default",
    nombre_comercial character varying(50) COLLATE pg_catalog."default",
    codigo_documento character varying(2) COLLATE pg_catalog."default",
    direccion_empresa character varying(100) COLLATE pg_catalog."default",
    fecha_emision date,
    direccion_establecimiento character varying(100) COLLATE pg_catalog."default",
    tipo_id_cliente character varying(1) COLLATE pg_catalog."default",
    razon_social_cliente character varying(100) COLLATE pg_catalog."default",
    identificacion_cliente character varying(20) COLLATE pg_catalog."default",
    direccion_cliente character varying(100) COLLATE pg_catalog."default",
    total_sin_impuesto numeric(15,2),
    total_descuento numeric(15,2),
    total_con_impuesto numeric(15,2),
    total_impuesto numeric(15,2),
    fecha_envio_sri date,
    motivo_rechazo character varying(50000) COLLATE pg_catalog."default",
    contribuyente_especial character varying(12) COLLATE pg_catalog."default",
    obligado_contabilidad character varying(2) COLLATE pg_catalog."default",
    moneda character varying(10) COLLATE pg_catalog."default",
    telefono_cliente character varying(20) COLLATE pg_catalog."default",
    correo_cliente character varying(50) COLLATE pg_catalog."default",
    codigo_impuesto character varying(1) COLLATE pg_catalog."default",
    codigo_impuesto_porcentaje character varying(1) COLLATE pg_catalog."default",
    tarifa character varying(5) COLLATE pg_catalog."default",
    propina character varying(5) COLLATE pg_catalog."default",
    estado_documento character varying(100) COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE mtd.pos_cab_fact_electronica
    OWNER to postgres;
	
	-- Table: mtd.pos_det_fact_electronica

-- DROP TABLE mtd.pos_det_fact_electronica;

CREATE TABLE mtd.pos_det_fact_electronica
(
    identificacion_empresa character varying(13) COLLATE pg_catalog."default" NOT NULL,
    establecimiento character varying(3) COLLATE pg_catalog."default" NOT NULL,
    punto_emision character varying(3) COLLATE pg_catalog."default" NOT NULL,
    secuencial character varying(9) COLLATE pg_catalog."default" NOT NULL,
    descripcion_producto character varying(40) COLLATE pg_catalog."default",
    cantidad_producto numeric(15,2),
    precio_unitario numeric(15,2),
    valor_descuento numeric(15,2),
    total_sin_impuesto numeric(15,2),
    codigo_principal character varying(10) COLLATE pg_catalog."default",
    codigo_auxiliar character varying(10) COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE mtd.pos_det_fact_electronica
    OWNER to postgres;
	
	-- Table: mtd.pos_nc_fact_electronica

-- DROP TABLE mtd.pos_nc_fact_electronica;

CREATE TABLE mtd.pos_nc_fact_electronica
(
    identificacion_empresa character varying(13) COLLATE pg_catalog."default" NOT NULL,
    establecimiento character varying(3) COLLATE pg_catalog."default" NOT NULL,
    punto_emision character varying(3) COLLATE pg_catalog."default" NOT NULL,
    secuencial_factura character varying(9) COLLATE pg_catalog."default" NOT NULL,
    secuencial_nc integer NOT NULL,
    tipo_documento_nc character varying(2) COLLATE pg_catalog."default" NOT NULL,
    tipo_doc_modificado character varying(2) COLLATE pg_catalog."default" NOT NULL,
    fecha_emision date NOT NULL,
    motivo_emision character varying(200) COLLATE pg_catalog."default" NOT NULL,
    clave_acceso_sri character varying(49) COLLATE pg_catalog."default" NOT NULL,
    estado_envio_sri character varying(20) COLLATE pg_catalog."default",
    motivo_rechazo_sri character varying(50000) COLLATE pg_catalog."default",
    CONSTRAINT pk_pos_nc_fact_electronica PRIMARY KEY (identificacion_empresa, establecimiento, punto_emision, secuencial_factura, secuencial_nc)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE mtd.pos_nc_fact_electronica
    OWNER to postgres;