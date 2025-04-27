package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.models.Usuario;
import TallerApi.TallerApi.service.UsuarioService;
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
}