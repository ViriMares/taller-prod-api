package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.LoginUsuarioDTO;
import TallerApi.TallerApi.models.LoginUsuario;
import TallerApi.TallerApi.service.LoginUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login-usuarios")
public class LoginUsuarioController {

    @Autowired
    private LoginUsuarioService loginUsuarioService;

    @PostMapping
    public LoginUsuario crearLoginUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        return loginUsuarioService.crearLoginUsuario(loginUsuarioDTO);
    }
}
