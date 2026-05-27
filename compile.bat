@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call mvnw clean install -DskipTests
echo.
echo === COMPILACION COMPLETADA ===
pause
