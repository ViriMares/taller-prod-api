package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.UsuarioDTO;
import TallerApi.TallerApi.models.Usuario;
import TallerApi.TallerApi.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario_GuardaUsuario_Correctamente() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombreUsuario("Juan");
        dto.setCorreoElectronico("juan@mail.com");
        dto.setNumeroTelefonico("123");
        dto.setNumeroSecundario("456");
        dto.setDireccion("Calle");
        dto.setPais("MX");
        dto.setTipoUsuario("Admin");

        Usuario saved = new Usuario();
        saved.setIdUsuario(1);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(saved);

        Usuario result = usuarioService.crearUsuario(dto);

        assertNotNull(result);
        assertEquals(1, result.getIdUsuario());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}
