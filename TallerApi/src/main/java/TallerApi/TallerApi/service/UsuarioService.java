package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.models.Usuario;
import TallerApi.TallerApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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