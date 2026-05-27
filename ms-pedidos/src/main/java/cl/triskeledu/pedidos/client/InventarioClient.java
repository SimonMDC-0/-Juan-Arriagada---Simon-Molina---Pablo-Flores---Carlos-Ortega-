package cl.triskeledu.pedidos.client;

import cl.triskeledu.pedidos.dto.InventarioRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-inventario")
public interface InventarioClient {

    @PostMapping("/api/inventario/salida")
    void registrarSalida(@RequestBody InventarioRequestDTO request);
}
