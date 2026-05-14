@echo off
echo ===== Iniciando Eureka Server =====
start "EUREKA" java -jar eureka\target\cl.triskeledu-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "MS-USUARIOS" java -jar ms-usuarios\target\cl.triskeledu-usuarios-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PRODUCTOS" java -jar ms-productos\target\cl.triskeledu-productos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-INVENTARIO" java -jar ms-inventario\target\cl.triskeledu-inventario-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-TIENDAS" java -jar ms-tiendas\target\cl.triskeledu-tiendas-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-CARRITO" java -jar ms-carrito\target\cl.triskeledu-carrito-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PEDIDOS" java -jar ms-pedidos\target\cl.triskeledu-pedidos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-LOGISTICA" java -jar ms-logistica\target\cl.triskeledu-logistica-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PROMOCIONES" java -jar ms-promociones\target\cl.triskeledu-promociones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-FACTURACION" java -jar ms-facturacion\target\cl.triskeledu-facturacion-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-SOPORTE" java -jar ms-soporte\target\cl.triskeledu-soporte-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
