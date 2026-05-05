package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.ExameRepository;
import br.com.sus.fila_api.Repository.FilaRepository;
import br.com.sus.fila_api.model.Exame;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ExameService {
    private final ExameRepository exameRepository;
    
    private final FilaRepository filaRepository;
    public ExameService(ExameRepository exameRepository, FilaRepository filaRepository){
        this.exameRepository = exameRepository;
        this.filaRepository = filaRepository;
    }
    public List<Exame> listar(){
        return exameRepository.findAll();
    }
    public Optional<Exame> buscarPorId(Long id){
        return exameRepository.findById(id);
    }
    public Exame adicionarExame(Exame exame){
        return exameRepository.save(exame);
    }

    public void deletarExame(Long id){
        filaRepository.deleteByExameId(id); // remove dependentes
        exameRepository.deleteById(id);     // agora pode deletar
    }


}
