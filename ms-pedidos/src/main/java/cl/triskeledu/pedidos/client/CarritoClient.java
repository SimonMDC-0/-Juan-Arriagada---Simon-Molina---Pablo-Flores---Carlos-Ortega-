package cl.triskeledu.pedidos.client;

import cl.triskeledu.pedidos.dto.CarritoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@FeignClient(name = "ms-carrito")
public interface CarritoClient {

    @GetMapping("/api/carrito/usuario/{usuarioId}")
    CarritoResponseDTO obtenerCarritoActivo(@PathVariable("usuarioId") Integer usuarioId);

    @DeleteMapping("/api/carrito/{carritoId}/vaciar")
    CarritoResponseDTO vaciarCarrito(@PathVariable("carritoId") Integer carritoId);
}
