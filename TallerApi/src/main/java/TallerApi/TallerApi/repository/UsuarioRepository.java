package TallerApi.TallerApi.repository;

import TallerApi.TallerApi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para encontrar un usuario por su nombre de usuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
