package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.ExameRepository;
import br.com.sus.fila_api.model.Exame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {
    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository){
        this.exameRepository = exameRepository;
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
        exameRepository.deleteById(id);
    }


}
