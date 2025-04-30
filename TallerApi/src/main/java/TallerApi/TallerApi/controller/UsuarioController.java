package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.models.Usuario;
import TallerApi.TallerApi.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping

    public Usuario crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }
    
        @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{IdUsurious}")
    public Optional<UsuarioDTO> getIdUsuario(@PathVariable Integer IdUsurious) {
        return usuarioService.getIdUsuario(IdUsurious);
    }
}