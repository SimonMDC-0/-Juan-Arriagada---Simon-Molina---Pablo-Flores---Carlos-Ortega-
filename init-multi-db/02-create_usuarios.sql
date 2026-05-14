
-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c usuarios

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS direcciones;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS roles;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
-- Usuarios es el dueño de la identidad, no requiere proyecciones aquí.

-- ============================================================
-- 3. DOMINIO USUARIOS
-- ============================================================
CREATE TABLE roles (
    id          SERIAL PRIMARY KEY,
    nombre_rol  VARCHAR(50) UNIQUE NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE usuarios (
    email         VARCHAR(150) PRIMARY KEY,
    nombre        VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol_id        INT NOT NULL REFERENCES roles(id),
    estado        VARCHAR(50) DEFAULT 'Activo'
);

CREATE TABLE direcciones (
    id            SERIAL PRIMARY KEY,
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios(email),
    calle         VARCHAR(255) NOT NULL,
    ciudad        VARCHAR(100) NOT NULL,
    region        VARCHAR(100) NOT NULL
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
-- Vacío

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO roles (nombre_rol, descripcion) VALUES
('Administrador', 'Control total del sistema EcoMarket'),
('Gerente', 'Administra inventario y tiendas'),
('Cliente', 'Comprador web');

INSERT INTO usuarios (email, nombre, password_hash, rol_id) VALUES
('admin@ecomarket.cl', 'Admin General', '$2a$10$hashAdmin', 1),
('gerente@ecomarket.cl', 'Gerente Lastarria', '$2a$10$hashGerente', 2),
('juan@cliente.cl', 'Juan Pérez', '$2a$10$hashCliente', 3);

INSERT INTO direcciones (usuario_email, calle, ciudad, region) VALUES
('juan@cliente.cl', 'Av. Providencia 123', 'Santiago', 'Metropolitana');