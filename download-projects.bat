@echo off
echo Descargando microservicios Spring Boot...
echo.
echo Descargando eureka.zip...
curl -o eureka.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=eureka&groupId=cl.triskeledu&artifactId=cl-triskeledu-eureka&name=tienda-eureka&description=servicio-eureka&packageName=cl.triskeledu.eureka&packaging=jar&javaVersion=21&dependencies=cloud-eureka-server,devtools"
echo.
echo Descargando ms-usuarios.zip...
curl -o ms-usuarios.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-usuarios&groupId=cl.triskeledu&artifactId=cl-triskeledu-usuarios&name=tienda-usuarios&description=servicio-usuarios&packageName=cl.triskeledu.usuarios&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-productos.zip...
curl -o ms-productos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-productos&groupId=cl.triskeledu&artifactId=cl-triskeledu-productos&name=tienda-productos&description=servicio-productos&packageName=cl.triskeledu.productos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-inventario.zip...
curl -o ms-inventario.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-inventario&groupId=cl.triskeledu&artifactId=cl-triskeledu-inventario&name=tienda-inventario&description=servicio-inventario&packageName=cl.triskeledu.inventario&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-tiendas.zip...
curl -o ms-tiendas.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-tiendas&groupId=cl.triskeledu&artifactId=cl-triskeledu-tiendas&name=tienda-tiendas&description=servicio-tiendas&packageName=cl.triskeledu.tiendas&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-carrito.zip...
curl -o ms-carrito.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-carrito&groupId=cl.triskeledu&artifactId=cl-triskeledu-carrito&name=tienda-carrito&description=servicio-carrito&packageName=cl.triskeledu.carrito&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-pedidos.zip...
curl -o ms-pedidos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-pedidos&groupId=cl.triskeledu&artifactId=cl-triskeledu-pedidos&name=tienda-pedidos&description=servicio-pedidos&packageName=cl.triskeledu.pedidos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-logistica.zip...
curl -o ms-logistica.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-logistica&groupId=cl.triskeledu&artifactId=cl-triskeledu-logistica&name=tienda-logistica&description=servicio-logistica&packageName=cl.triskeledu.logistica&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-promociones.zip...
curl -o ms-promociones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-promociones&groupId=cl.triskeledu&artifactId=cl-triskeledu-promociones&name=tienda-promociones&description=servicio-promociones&packageName=cl.triskeledu.promociones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-facturacion.zip...
curl -o ms-facturacion.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-facturacion&groupId=cl.triskeledu&artifactId=cl-triskeledu-facturacion&name=tienda-facturacion&description=servicio-facturacion&packageName=cl.triskeledu.facturacion&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-soporte.zip...
curl -o ms-soporte.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-soporte&groupId=cl.triskeledu&artifactId=cl-triskeledu-soporte&name=tienda-soporte&description=servicio-soporte&packageName=cl.triskeledu.soporte&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descarga completada.
pause
