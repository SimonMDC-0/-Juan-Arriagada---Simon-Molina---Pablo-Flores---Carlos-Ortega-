package cl.triskeledu.pedidos.service;

import cl.triskeledu.pedidos.dto.PedidoRequestDTO;
import cl.triskeledu.pedidos.dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {
    PedidoResponseDTO crearPedido(PedidoRequestDTO request);
    PedidoResponseDTO obtenerPedidoPorId(Integer id);
    List<PedidoResponseDTO> listarPedidosPorUsuario(Integer usuarioId);
    PedidoResponseDTO actualizarEstadoPedido(Integer id, String nuevoEstado);
}
