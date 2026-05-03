package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilaService {

    public final FilaRepository filaRepository;
}
