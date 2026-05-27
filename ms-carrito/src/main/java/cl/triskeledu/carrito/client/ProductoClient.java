package cl.triskeledu.carrito.client;

import cl.triskeledu.carrito.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-productos")
public interface ProductoClient {

    @GetMapping("/api/productos/sku/{sku}")
    ProductoDTO obtenerProductoPorSku(@PathVariable("sku") String sku);
}
