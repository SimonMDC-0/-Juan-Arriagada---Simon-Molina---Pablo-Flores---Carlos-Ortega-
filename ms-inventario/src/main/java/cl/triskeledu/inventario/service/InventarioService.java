package cl.triskeledu.inventario.service;

import cl.triskeledu.inventario.dto.InventarioRequestDTO;
import cl.triskeledu.inventario.dto.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {
    InventarioResponseDTO registrarIngreso(InventarioRequestDTO request);
    InventarioResponseDTO registrarSalida(InventarioRequestDTO request);
    List<InventarioResponseDTO> obtenerInventarioPorProducto(String productoSku);
    List<InventarioResponseDTO> obtenerInventarioPorTienda(Integer tiendaId);
    InventarioResponseDTO obtenerStockEspecifico(String productoSku, Integer tiendaId);
}
