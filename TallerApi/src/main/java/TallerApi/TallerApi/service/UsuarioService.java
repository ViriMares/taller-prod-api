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

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getCorreoElectronico(), usuario.getNumeroTelefonico(), usuario.getDireccion(), usuario.getTipoUsuario()))
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> getIdUsuario(int IdUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(IdUsuario);
        return usuario.map(u -> new UsuarioDTO(u.getIdUsuario(), u.getNombreUsuario(), u.getCorreoElectronico(), u.getNumeroTelefonico(), u.getDireccion(), u.getTipoUsuario()));
    }

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        usuario.setNumeroTelefonico(usuarioDTO.getNumeroTelefonico());
        usuario.setNumeroSecundario(usuarioDTO.getNumeroSecundario());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setPais(usuarioDTO.getPais());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());

        return usuarioRepository.save(usuario);
    }
}
