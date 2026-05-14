-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c inventario

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS movimientos_stock;
DROP TABLE IF EXISTS stock_tiendas;
DROP TABLE IF EXISTS productos_ref;
DROP TABLE IF EXISTS tiendas_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE productos_ref (
    sku    VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL
);

CREATE TABLE tiendas_ref (
    id     INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- ============================================================
-- 3. DOMINIO INVENTARIO
-- ============================================================
CREATE TABLE stock_tiendas (
    id           SERIAL PRIMARY KEY,
    tienda_id    INT NOT NULL REFERENCES tiendas_ref(id),
    producto_sku VARCHAR(50) NOT NULL REFERENCES productos_ref(sku),
    cantidad     INT DEFAULT 0,
    fecha_act    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movimientos_stock (
    id          SERIAL PRIMARY KEY,
    stock_id    INT NOT NULL REFERENCES stock_tiendas(id),
    tipo_mov    VARCHAR(20) CHECK (tipo_mov IN ('Entrada', 'Salida')),
    cantidad    INT NOT NULL,
    fecha_mov   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO productos_ref (sku, nombre) VALUES
('ECO-CEP-001', 'Cepillo de Dientes de Bambú'),
('ECO-BOL-001', 'Bolsa de Algodón Reutilizable');

INSERT INTO tiendas_ref (id, nombre) VALUES
(1, 'Tienda Lastarria'),
(2, 'Tienda Valdivia');

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO stock_tiendas (tienda_id, producto_sku, cantidad) VALUES
(1, 'ECO-CEP-001', 150),
(2, 'ECO-BOL-001', 80);