package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
