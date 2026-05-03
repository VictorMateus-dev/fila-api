package br.com.sus.fila_api.Repository;

import br.com.sus.fila_api.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {
}
