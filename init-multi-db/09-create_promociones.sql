-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c promociones

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS usos_cupon;
DROP TABLE IF EXISTS cupones_descuento;
DROP TABLE IF EXISTS usuarios_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE usuarios_ref ( email VARCHAR(150) PRIMARY KEY );

-- ============================================================
-- 3. DOMINIO PROMOCIONES
-- ============================================================
CREATE TABLE cupones_descuento (
    id              SERIAL PRIMARY KEY,
    codigo          VARCHAR(50) UNIQUE NOT NULL,
    descuento_pct   INT NOT NULL CHECK (descuento_pct BETWEEN 1 AND 100),
    fecha_expira    DATE NOT NULL,
    activo          BOOLEAN DEFAULT TRUE
);

CREATE TABLE usos_cupon (
    id            SERIAL PRIMARY KEY,
    cupon_id      INT NOT NULL REFERENCES cupones_descuento(id),
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    pedido_id_ext INT NOT NULL, -- Referencia externa sin FK directa
    fecha_uso     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO usuarios_ref (email) VALUES ('juan@cliente.cl');

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO cupones_descuento (codigo, descuento_pct, fecha_expira) VALUES ('ECO10', 10, '2026-12-31');