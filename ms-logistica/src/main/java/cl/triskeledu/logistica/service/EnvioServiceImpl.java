package cl.triskeledu.logistica.service;

import cl.triskeledu.logistica.dto.EnvioRequestDTO;
import cl.triskeledu.logistica.dto.EnvioResponseDTO;
import cl.triskeledu.logistica.exceptions.BadRequestException;
import cl.triskeledu.logistica.exceptions.ResourceNotFoundException;
import cl.triskeledu.logistica.model.Envio;
import cl.triskeledu.logistica.repository.EnvioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;

    public EnvioServiceImpl(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Override
    @Transactional
    public EnvioResponseDTO programarEnvio(EnvioRequestDTO request) {
        if (envioRepository.findByPedidoId(request.pedidoId()).isPresent()) {
            throw new BadRequestException("Ya existe un envío programado para el pedido " + request.pedidoId());
        }

        Envio envio = new Envio(
                null,
                request.pedidoId(),
                request.direccion(),
                "PENDIENTE",
                null,
                null
        );

        Envio saved = envioRepository.save(envio);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioResponseDTO obtenerEnvioPorPedido(Integer pedidoId) {
        Envio envio = envioRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Envío para el pedido " + pedidoId + " no encontrado"));
        return mapToResponse(envio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> listarEnvios() {
        return envioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EnvioResponseDTO despacharEnvio(Integer id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío " + id + " no encontrado"));

        if (!"PENDIENTE".equals(envio.getEstado())) {
            throw new BadRequestException("Solo envíos en estado PENDIENTE pueden ser despachados");
        }

        envio.setEstado("DESPACHADO");
        envio.setFechaDespacho(LocalDateTime.now());
        return mapToResponse(envioRepository.save(envio));
    }

    @Override
    @Transactional
    public EnvioResponseDTO entregarEnvio(Integer id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío " + id + " no encontrado"));

        if (!"DESPACHADO".equals(envio.getEstado())) {
            throw new BadRequestException("Solo envíos en estado DESPACHADO pueden ser entregados");
        }

        envio.setEstado("ENTREGADO");
        envio.setFechaEntrega(LocalDateTime.now());
        return mapToResponse(envioRepository.save(envio));
    }

    private EnvioResponseDTO mapToResponse(Envio envio) {
        return new EnvioResponseDTO(
                envio.getId(),
                envio.getPedidoId(),
                envio.getDireccion(),
                envio.getEstado(),
                envio.getFechaDespacho(),
                envio.getFechaEntrega()
        );
    }
}
