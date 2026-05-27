package cl.triskeledu.usuarios.service;

import cl.triskeledu.usuarios.dto.DireccionRequestDTO;
import cl.triskeledu.usuarios.dto.DireccionResponseDTO;
import cl.triskeledu.usuarios.dto.UsuarioRequestDTO;
import cl.triskeledu.usuarios.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request);
    UsuarioResponseDTO obtenerUsuarioPorEmail(String email);
    List<UsuarioResponseDTO> listarUsuarios();
    
    DireccionResponseDTO agregarDireccion(DireccionRequestDTO request);
    List<DireccionResponseDTO> listarDireccionesPorUsuario(String email);
}
