package cl.triskeledu.tiendas.service;

import cl.triskeledu.tiendas.dto.TiendaRequestDTO;
import cl.triskeledu.tiendas.dto.TiendaResponseDTO;

import java.util.List;

public interface TiendaService {
    TiendaResponseDTO crearTienda(TiendaRequestDTO request);
    TiendaResponseDTO obtenerTiendaPorId(Integer id);
    List<TiendaResponseDTO> listarTiendas();
}
