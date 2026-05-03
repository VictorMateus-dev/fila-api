package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilaRepository extends JpaRepository<Fila,Long> {
    List<Fila> findByExameIdOrderByPrioridadeNivelDescDataSolicitacaoAsc(Long exameId);
}
