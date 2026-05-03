package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.PrioridadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrioridadeService {

    public final PrioridadeRepository prioridadeRepository;
}
