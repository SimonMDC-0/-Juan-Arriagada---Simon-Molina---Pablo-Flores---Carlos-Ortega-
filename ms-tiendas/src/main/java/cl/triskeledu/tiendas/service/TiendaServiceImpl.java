package cl.triskeledu.tiendas.service;

import cl.triskeledu.tiendas.dto.TiendaRequestDTO;
import cl.triskeledu.tiendas.dto.TiendaResponseDTO;
import cl.triskeledu.tiendas.exceptions.ResourceNotFoundException;
import cl.triskeledu.tiendas.model.Tienda;
import cl.triskeledu.tiendas.repository.TiendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TiendaServiceImpl implements TiendaService {

    private final TiendaRepository tiendaRepository;

    public TiendaServiceImpl(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    @Override
    @Transactional
    public TiendaResponseDTO crearTienda(TiendaRequestDTO request) {
        Tienda tienda = new Tienda(null, request.nombre(), request.direccion(), request.telefono(), request.horario());
        Tienda saved = tiendaRepository.save(tienda);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TiendaResponseDTO obtenerTiendaPorId(Integer id) {
        Tienda tienda = tiendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + id));
        return mapToResponse(tienda);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiendaResponseDTO> listarTiendas() {
        return tiendaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TiendaResponseDTO mapToResponse(Tienda tienda) {
        return new TiendaResponseDTO(
                tienda.getId(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getTelefono(),
                tienda.getHorario()
        );
    }
}
