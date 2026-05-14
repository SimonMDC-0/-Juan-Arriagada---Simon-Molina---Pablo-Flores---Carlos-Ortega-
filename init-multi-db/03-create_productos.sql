-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c productos

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS imagenes;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
-- No requiere proyecciones obligatorias en su versión básica.

-- ============================================================
-- 3. DOMINIO PRODUCTOS
-- ============================================================
CREATE TABLE categorias (
    id               SERIAL PRIMARY KEY,
    nombre_categoria VARCHAR(100) UNIQUE NOT NULL,
    descripcion      VARCHAR(255)
);

CREATE TABLE productos (
    sku          VARCHAR(50) PRIMARY KEY,
    nombre       VARCHAR(150) NOT NULL,
    precio       INT NOT NULL,
    descripcion  TEXT,
    categoria_id INT REFERENCES categorias(id)
);

CREATE TABLE imagenes (
    id           SERIAL PRIMARY KEY,
    producto_sku VARCHAR(50) NOT NULL REFERENCES productos(sku),
    url_imagen   VARCHAR(255) NOT NULL,
    es_principal BOOLEAN DEFAULT FALSE
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
-- Vacío

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO categorias (nombre_categoria, descripcion) VALUES 
('Cuidado Personal', 'Productos de higiene eco-amigables'),
('Hogar', 'Artículos sostenibles para la casa');

INSERT INTO productos (sku, nombre, precio, descripcion, categoria_id) VALUES
('ECO-CEP-001', 'Cepillo de Dientes de Bambú', 2500, 'Cepillo 100% biodegradable', 1),
('ECO-BOL-001', 'Bolsa de Algodón Reutilizable', 3000, 'Ideal para compras a granel', 2);