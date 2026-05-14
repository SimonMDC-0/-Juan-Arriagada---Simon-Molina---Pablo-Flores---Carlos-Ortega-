-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c carrito

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS items_carrito;
DROP TABLE IF EXISTS carritos_activos;
DROP TABLE IF EXISTS usuarios_ref;
DROP TABLE IF EXISTS productos_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE usuarios_ref ( email VARCHAR(150) PRIMARY KEY );
CREATE TABLE productos_ref ( sku VARCHAR(50) PRIMARY KEY, precio INT NOT NULL );

-- ============================================================
-- 3. DOMINIO CARRITO
-- ============================================================
CREATE TABLE carritos_activos (
    id            SERIAL PRIMARY KEY,
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    fecha_creada  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_temp    INT DEFAULT 0
);

CREATE TABLE items_carrito (
    id           SERIAL PRIMARY KEY,
    carrito_id   INT NOT NULL REFERENCES carritos_activos(id),
    producto_sku VARCHAR(50) NOT NULL REFERENCES productos_ref(sku),
    cantidad     INT NOT NULL,
    subtotal     INT NOT NULL
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO usuarios_ref (email) VALUES ('juan@cliente.cl');
INSERT INTO productos_ref (sku, precio) VALUES ('ECO-CEP-001', 2500);

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO carritos_activos (usuario_email, total_temp) VALUES ('juan@cliente.cl', 5000);
INSERT INTO items_carrito (carrito_id, producto_sku, cantidad, subtotal) VALUES (1, 'ECO-CEP-001', 2, 5000);