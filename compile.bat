@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call cd C:\tienda-test\ms-usuarios
call mvn clean install -U
call cd C:\tienda-test\ms-productos
call mvn clean install -U
call cd C:\tienda-test\ms-inventario
call mvn clean install -U
call cd C:\tienda-test\ms-tiendas
call mvn clean install -U
call cd C:\tienda-test\ms-carrito
call mvn clean install -U
call cd C:\tienda-test\ms-pedidos
call mvn clean install -U
call cd C:\tienda-test\ms-logistica
call mvn clean install -U
call cd C:\tienda-test\ms-promociones
call mvn clean install -U
call cd C:\tienda-test\ms-facturacion
call mvn clean install -U
call cd C:\tienda-test\ms-soporte
call mvn clean install -U
echo.
echo === COMPILACION COMPLETADA ===
pause
