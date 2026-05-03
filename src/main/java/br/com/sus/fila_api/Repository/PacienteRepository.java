package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
}
