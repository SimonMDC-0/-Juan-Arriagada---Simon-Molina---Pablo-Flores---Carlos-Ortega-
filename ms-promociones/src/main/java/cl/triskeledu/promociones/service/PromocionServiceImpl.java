package cl.triskeledu.promociones.service;

import cl.triskeledu.promociones.dto.PromocionRequestDTO;
import cl.triskeledu.promociones.dto.PromocionResponseDTO;
import cl.triskeledu.promociones.exceptions.BadRequestException;
import cl.triskeledu.promociones.exceptions.ResourceNotFoundException;
import cl.triskeledu.promociones.model.Promocion;
import cl.triskeledu.promociones.repository.PromocionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository promocionRepository;

    public PromocionServiceImpl(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    @Override
    @Transactional
    public PromocionResponseDTO crearPromocion(PromocionRequestDTO request) {
        if (promocionRepository.findByCodigo(request.codigo()).isPresent()) {
            throw new BadRequestException("Ya existe una promoción con el código " + request.codigo());
        }

        Promocion promocion = new Promocion(
                null,
                request.codigo(),
                request.descripcion(),
                request.porcentajeDescuento(),
                request.fechaInicio(),
                request.fechaFin(),
                request.activa() != null ? request.activa() : true
        );

        Promocion saved = promocionRepository.save(promocion);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PromocionResponseDTO obtenerPromocionPorCodigo(String codigo) {
        Promocion promocion = promocionRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción " + codigo + " no encontrada"));
        return mapToResponse(promocion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromocionResponseDTO> listarPromociones() {
        return promocionRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PromocionResponseDTO aplicarPromocion(String codigo) {
        Promocion promocion = promocionRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción " + codigo + " no encontrada"));

        LocalDateTime now = LocalDateTime.now();
        if (!promocion.getActiva() || now.isBefore(promocion.getFechaInicio()) || now.isAfter(promocion.getFechaFin())) {
            throw new BadRequestException("La promoción " + codigo + " no está activa o expiró");
        }

        return mapToResponse(promocion);
    }

    private PromocionResponseDTO mapToResponse(Promocion promocion) {
        return new PromocionResponseDTO(
                promocion.getId(),
                promocion.getCodigo(),
                promocion.getDescripcion(),
                promocion.getPorcentajeDescuento(),
                promocion.getFechaInicio(),
                promocion.getFechaFin(),
                promocion.getActiva()
        );
    }
}
