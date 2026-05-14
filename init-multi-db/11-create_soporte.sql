-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c soporte

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS tickets_soporte;
DROP TABLE IF EXISTS resenas_productos;
DROP TABLE IF EXISTS usuarios_ref;
DROP TABLE IF EXISTS productos_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE usuarios_ref ( email VARCHAR(150) PRIMARY KEY );
CREATE TABLE productos_ref ( sku VARCHAR(50) PRIMARY KEY );

-- ============================================================
-- 3. DOMINIO SOPORTE
-- ============================================================
CREATE TABLE resenas_productos (
    id            SERIAL PRIMARY KEY,
    producto_sku  VARCHAR(50) NOT NULL REFERENCES productos_ref(sku),
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    calificacion  INT CHECK (calificacion BETWEEN 1 AND 5),
    comentario    TEXT
);

CREATE TABLE tickets_soporte (
    id            SERIAL PRIMARY KEY,
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    motivo        VARCHAR(150) NOT NULL,
    detalle       TEXT NOT NULL,
    estado        VARCHAR(50) DEFAULT 'Abierto'
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO usuarios_ref (email) VALUES ('juan@cliente.cl');
INSERT INTO productos_ref (sku) VALUES ('ECO-CEP-001');

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO resenas_productos (producto_sku, usuario_email, calificacion, comentario) 
VALUES ('ECO-CEP-001', 'juan@cliente.cl', 5, 'Excelente calidad, muy recomendado.');

INSERT INTO tickets_soporte (usuario_email, motivo, detalle) 
VALUES ('juan@cliente.cl', 'Duda sobre envío', '¿Cuánto demora a regiones?');