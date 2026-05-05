package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FilaRepository extends JpaRepository<Fila,Long> {
    List<Fila> findByExameIdOrderByDataSolicitacaoAsc(Long exameId);

    @Modifying
    @Transactional
    void deleteByExameId(Long exameId);
}
