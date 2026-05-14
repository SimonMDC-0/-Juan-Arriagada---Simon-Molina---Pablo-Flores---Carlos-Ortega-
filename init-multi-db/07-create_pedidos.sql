-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c pedidos

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS historial_estados_pedido;
DROP TABLE IF EXISTS detalles_pedido;
DROP TABLE IF EXISTS ordenes_pedido;
DROP TABLE IF EXISTS usuarios_ref;
DROP TABLE IF EXISTS productos_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE usuarios_ref ( email VARCHAR(150) PRIMARY KEY );
CREATE TABLE productos_ref ( sku VARCHAR(50) PRIMARY KEY );

-- ============================================================
-- 3. DOMINIO PEDIDOS
-- ============================================================
CREATE TABLE ordenes_pedido (
    id            SERIAL PRIMARY KEY,
    usuario_email VARCHAR(150) NOT NULL REFERENCES usuarios_ref(email),
    fecha_compra  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_pagado  INT NOT NULL,
    estado_actual VARCHAR(50) DEFAULT 'Procesando'
);

CREATE TABLE detalles_pedido (
    id           SERIAL PRIMARY KEY,
    orden_id     INT NOT NULL REFERENCES ordenes_pedido(id),
    producto_sku VARCHAR(50) NOT NULL REFERENCES productos_ref(sku),
    cantidad     INT NOT NULL,
    precio_fijo  INT NOT NULL
);

CREATE TABLE historial_estados_pedido (
    id            SERIAL PRIMARY KEY,
    orden_id      INT NOT NULL REFERENCES ordenes_pedido(id),
    estado_previo VARCHAR(50),
    estado_nuevo  VARCHAR(50) NOT NULL,
    fecha_cambio  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO usuarios_ref (email) VALUES ('juan@cliente.cl');
INSERT INTO productos_ref (sku) VALUES ('ECO-BOL-001');

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO ordenes_pedido (usuario_email, total_pagado) VALUES ('juan@cliente.cl', 3000);
INSERT INTO detalles_pedido (orden_id, producto_sku, cantidad, precio_fijo) VALUES (1, 'ECO-BOL-001', 1, 3000);