package TallerApi.TallerApi.repository;

import TallerApi.TallerApi.models.LoginUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUsuarioRepository extends JpaRepository<LoginUsuario, Integer> {

    // Método personalizado para encontrar un usuario por su correo electrónico
    LoginUsuario findByCorreoElectronico(String correoElectronico);
}
