package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.models.Usuario;
import TallerApi.TallerApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getCorreoElectronico(), usuario.getNumeroTelefonico(), usuario.getDireccion(), usuario.getTipoUsuario()))
                .collect(Collectors.toList());
    }

    // Obtener un usuario por nombre
    public Optional<UsuarioDTO> getUsuarioByNombre(String nombreUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        return usuario.map(u -> new UsuarioDTO(u.getIdUsuario(), u.getNombreUsuario(), u.getCorreoElectronico(), u.getNumeroTelefonico(), u.getDireccion(), u.getTipoUsuario()));
    }
}
