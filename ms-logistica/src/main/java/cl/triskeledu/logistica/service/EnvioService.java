package cl.triskeledu.logistica.service;

import cl.triskeledu.logistica.dto.EnvioRequestDTO;
import cl.triskeledu.logistica.dto.EnvioResponseDTO;

import java.util.List;

public interface EnvioService {
    EnvioResponseDTO programarEnvio(EnvioRequestDTO request);
    EnvioResponseDTO obtenerEnvioPorPedido(Integer pedidoId);
    List<EnvioResponseDTO> listarEnvios();
    EnvioResponseDTO despacharEnvio(Integer id);
    EnvioResponseDTO entregarEnvio(Integer id);
}
