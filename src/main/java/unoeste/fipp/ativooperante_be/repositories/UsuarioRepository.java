package unoeste.fipp.ativooperante_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


}
