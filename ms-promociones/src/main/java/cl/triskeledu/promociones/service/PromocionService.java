package cl.triskeledu.promociones.service;

import cl.triskeledu.promociones.dto.PromocionRequestDTO;
import cl.triskeledu.promociones.dto.PromocionResponseDTO;

import java.util.List;

public interface PromocionService {
    PromocionResponseDTO crearPromocion(PromocionRequestDTO request);
    PromocionResponseDTO obtenerPromocionPorCodigo(String codigo);
    List<PromocionResponseDTO> listarPromociones();
    PromocionResponseDTO aplicarPromocion(String codigo);
}
