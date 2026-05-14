-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c logistica

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS rutas_despacho;
DROP TABLE IF EXISTS envios;
DROP TABLE IF EXISTS transportistas;
DROP TABLE IF EXISTS pedidos_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE pedidos_ref ( id INT PRIMARY KEY );

-- ============================================================
-- 3. DOMINIO LOGÍSTICA
-- ============================================================
CREATE TABLE transportistas (
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    vehiculo  VARCHAR(50) NOT NULL,
    telefono  VARCHAR(20)
);

CREATE TABLE envios (
    id                SERIAL PRIMARY KEY,
    pedido_id         INT NOT NULL REFERENCES pedidos_ref(id),
    direccion_destino VARCHAR(255) NOT NULL,
    estado_envio      VARCHAR(50) DEFAULT 'En Bodega',
    fecha_estimada    DATE
);

CREATE TABLE rutas_despacho (
    id               SERIAL PRIMARY KEY,
    envio_id         INT NOT NULL REFERENCES envios(id),
    transportista_id INT NOT NULL REFERENCES transportistas(id),
    orden_ruta       INT NOT NULL,
    entregado        BOOLEAN DEFAULT FALSE
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO pedidos_ref (id) VALUES (1);

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO transportistas (nombre, vehiculo) VALUES ('Logística Verde', 'Furgón Eléctrico');
INSERT INTO envios (pedido_id, direccion_destino) VALUES (1, 'Av. Providencia 123, Santiago');