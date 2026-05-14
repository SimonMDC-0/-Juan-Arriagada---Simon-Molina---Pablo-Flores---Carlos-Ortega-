@echo off
echo ===== Iniciando Eureka Server =====
start "eureka" mvn -f eureka spring-boot:run

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "ms-usuarios" mvn -f ms-usuarios spring-boot:run
start "ms-productos" mvn -f ms-productos spring-boot:run
start "ms-inventario" mvn -f ms-inventario spring-boot:run
start "ms-tiendas" mvn -f ms-tiendas spring-boot:run
start "ms-carrito" mvn -f ms-carrito spring-boot:run
start "ms-pedidos" mvn -f ms-pedidos spring-boot:run
start "ms-logistica" mvn -f ms-logistica spring-boot:run
start "ms-promociones" mvn -f ms-promociones spring-boot:run
start "ms-facturacion" mvn -f ms-facturacion spring-boot:run
start "ms-soporte" mvn -f ms-soporte spring-boot:run
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
