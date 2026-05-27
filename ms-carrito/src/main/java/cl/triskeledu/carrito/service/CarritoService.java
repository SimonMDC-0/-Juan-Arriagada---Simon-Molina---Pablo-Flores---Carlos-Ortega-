package cl.triskeledu.carrito.service;

import cl.triskeledu.carrito.dto.CarritoItemRequestDTO;
import cl.triskeledu.carrito.dto.CarritoItemResponseDTO;
import cl.triskeledu.carrito.dto.CarritoRequestDTO;
import cl.triskeledu.carrito.dto.CarritoResponseDTO;

public interface CarritoService {
    CarritoResponseDTO crearCarrito(CarritoRequestDTO request);
    CarritoResponseDTO obtenerCarritoActivo(Integer usuarioId);
    CarritoResponseDTO agregarItem(CarritoItemRequestDTO request);
    CarritoResponseDTO removerItem(Integer itemId);
    CarritoResponseDTO vaciarCarrito(Integer carritoId);
}
