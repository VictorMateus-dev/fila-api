package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.PrioridadeRepository;
import br.com.sus.fila_api.model.Prioridade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrioridadeService {

    public final PrioridadeRepository prioridadeRepository;

    public List<Prioridade> listarPrioridade(){
        return prioridadeRepository.findAll();
    }

    public Optional<Prioridade> buscarPorId(Long id){
        return prioridadeRepository.findById(id);
    }
}
