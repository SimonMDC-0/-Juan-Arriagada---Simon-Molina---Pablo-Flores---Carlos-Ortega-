@echo off
echo.
echo === REINSTALACION DE DEPENDENCIAS MAVEN ===
echo.

REM Paso 1: Eliminar carpeta local de dependencias
echo Eliminando carpeta .m2 ...
rmdir /s /q %USERPROFILE%\.m2

REM Paso 2: Descargar e instalar todas las dependencias
echo Descargando dependencias nuevamente con Maven Wrapper ...
call mvnw clean install -U -DskipTests

echo.
echo === PROCESO COMPLETADO ===
pause
