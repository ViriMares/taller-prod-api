package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.LoginUsuarioDTO;
import TallerApi.TallerApi.models.LoginUsuario;
import TallerApi.TallerApi.repository.LoginUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUsuarioService {

    @Autowired
    private LoginUsuarioRepository loginUsuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Crear un nuevo usuario de login
    public LoginUsuario crearLoginUsuario(LoginUsuarioDTO loginUsuarioDTO) {
        LoginUsuario loginUsuario = new LoginUsuario();
        loginUsuario.setNombreUsuario(loginUsuarioDTO.getNombreUsuario());
        loginUsuario.setNombreCompleto(loginUsuarioDTO.getNombreCompleto());
        loginUsuario.setCorreoElectronico(loginUsuarioDTO.getCorreoElectronico());
        loginUsuario.setContrasenaHash(passwordEncoder.encode(loginUsuarioDTO.getContrasena()));
        return loginUsuarioRepository.save(loginUsuario);
    }

    // Validar las credenciales del usuario
    public boolean validarCredenciales(String correo, String contrasena) {
        LoginUsuario loginUsuario = loginUsuarioRepository.findByCorreoElectronico(correo);
        if (loginUsuario != null) {
            return passwordEncoder.matches(contrasena, loginUsuario.getContrasenaHash());
        }
        return false;
    }
}
