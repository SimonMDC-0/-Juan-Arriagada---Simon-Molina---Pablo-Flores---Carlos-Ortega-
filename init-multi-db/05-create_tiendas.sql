-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c tiendas

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS empleados_tienda;
DROP TABLE IF EXISTS horarios_tienda;
DROP TABLE IF EXISTS sucursales;
DROP TABLE IF EXISTS usuarios_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE usuarios_ref (
    email  VARCHAR(150) PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL
);

-- ============================================================
-- 3. DOMINIO TIENDAS
-- ============================================================
CREATE TABLE sucursales (
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    ciudad    VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    estado    VARCHAR(50) DEFAULT 'Operativa'
);

CREATE TABLE horarios_tienda (
    id            SERIAL PRIMARY KEY,
    sucursal_id   INT NOT NULL REFERENCES sucursales(id),
    dias          VARCHAR(100) NOT NULL,
    hora_apertura TIME NOT NULL,
    hora_cierre   TIME NOT NULL
);

CREATE TABLE empleados_tienda (
    id            SERIAL PRIMARY KEY,
    sucursal_id   INT NOT NULL REFERENCES sucursales(id),
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    cargo         VARCHAR(50) NOT NULL
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO usuarios_ref (email, nombre) VALUES
('gerente@ecomarket.cl', 'Gerente Lastarria');

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO sucursales (nombre, ciudad, direccion) VALUES
('Tienda Lastarria', 'Santiago', 'José Victorino Lastarria 123'),
('Tienda Valdivia', 'Valdivia', 'Los Robles 456'),
('Tienda Antofagasta', 'Antofagasta', 'Prat 789');

INSERT INTO empleados_tienda (sucursal_id, usuario_email, cargo) VALUES
(1, 'gerente@ecomarket.cl', 'Gerente de Sucursal');