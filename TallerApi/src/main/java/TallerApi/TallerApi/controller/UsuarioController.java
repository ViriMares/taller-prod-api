package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // Obtener un usuario por nombre
    @GetMapping("/{nombreUsuario}")
    public Optional<UsuarioDTO> getUsuario(@PathVariable String nombreUsuario) {
        return usuarioService.getUsuarioByNombre(nombreUsuario);
    }
}
