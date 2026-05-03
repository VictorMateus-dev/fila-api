package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    public final NotificacaoRepository notificacaoRepository;
}
