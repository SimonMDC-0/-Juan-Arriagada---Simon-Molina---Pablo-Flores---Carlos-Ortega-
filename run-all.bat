@echo off
echo ===== Iniciando Eureka Server =====
start "eureka" cmd /c "cd eureka && mvnw spring-boot:run"

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "ms-usuarios" cmd /c "cd ms-usuarios && mvnw spring-boot:run"
start "ms-productos" cmd /c "cd ms-productos && mvnw spring-boot:run"
start "ms-inventario" cmd /c "cd ms-inventario && mvnw spring-boot:run"
start "ms-tiendas" cmd /c "cd ms-tiendas && mvnw spring-boot:run"
start "ms-carrito" cmd /c "cd ms-carrito && mvnw spring-boot:run"
start "ms-pedidos" cmd /c "cd ms-pedidos && mvnw spring-boot:run"
start "ms-logistica" cmd /c "cd ms-logistica && mvnw spring-boot:run"
start "ms-promociones" cmd /c "cd ms-promociones && mvnw spring-boot:run"
start "ms-facturacion" cmd /c "cd ms-facturacion && mvnw spring-boot:run"
start "ms-soporte" cmd /c "cd ms-soporte && mvnw spring-boot:run"
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
