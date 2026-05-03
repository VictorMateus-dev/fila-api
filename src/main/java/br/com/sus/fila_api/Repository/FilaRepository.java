package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilaRepository extends JpaRepository<Fila,Long> {
}
