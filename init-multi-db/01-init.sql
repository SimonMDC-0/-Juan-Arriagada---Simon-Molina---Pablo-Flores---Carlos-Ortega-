SELECT 'CREATE DATABASE usuarios'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'usuarios') \gexec

SELECT 'CREATE DATABASE productos'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'productos') \gexec

SELECT 'CREATE DATABASE inventario'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'inventario') \gexec

SELECT 'CREATE DATABASE tiendas'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'tiendas') \gexec

SELECT 'CREATE DATABASE carrito'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'carrito') \gexec

SELECT 'CREATE DATABASE pedidos'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'pedidos') \gexec

SELECT 'CREATE DATABASE logistica'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'logistica') \gexec

SELECT 'CREATE DATABASE promociones'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'promociones') \gexec

SELECT 'CREATE DATABASE facturacion'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'facturacion') \gexec

SELECT 'CREATE DATABASE soporte'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'soporte') \gexec