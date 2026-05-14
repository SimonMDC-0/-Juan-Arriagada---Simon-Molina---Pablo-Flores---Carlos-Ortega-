-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

\c facturacion

-- ============================================================
-- 1. ELIMINACIÓN
-- ============================================================
DROP TABLE IF EXISTS detalles_factura;
DROP TABLE IF EXISTS facturas_emitidas;
DROP TABLE IF EXISTS pedidos_ref;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE pedidos_ref (
    id           INT PRIMARY KEY,
    total_pagado INT NOT NULL
);

-- ============================================================
-- 3. DOMINIO FACTURACIÓN
-- ============================================================
CREATE TABLE facturas_emitidas (
    id             SERIAL PRIMARY KEY,
    pedido_id      INT NOT NULL REFERENCES pedidos_ref(id),
    folio_sii      VARCHAR(50) UNIQUE NOT NULL,
    rut_cliente    VARCHAR(20) NOT NULL,
    fecha_emision  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE detalles_factura (
    id             SERIAL PRIMARY KEY,
    factura_id     INT NOT NULL REFERENCES facturas_emitidas(id),
    glosa_item     VARCHAR(200) NOT NULL,
    monto_neto     INT NOT NULL,
    monto_iva      INT NOT NULL
);

-- ============================================================
-- 4. POBLAMIENTO DE PROYECCIONES
-- ============================================================
INSERT INTO pedidos_ref (id, total_pagado) VALUES (1, 3000);

-- ============================================================
-- 5. INSERCIÓN DE DATOS EN DOMINIO
-- ============================================================
INSERT INTO facturas_emitidas (pedido_id, folio_sii, rut_cliente) VALUES (1, 'FOLIO-0001', '19.123.456-7');