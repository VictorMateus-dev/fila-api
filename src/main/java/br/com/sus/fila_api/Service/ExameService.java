package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.ExameRepository;
import org.springframework.stereotype.Service;

@Service
public class ExameService {
    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository){
        this.exameRepository = exameRepository;
    }

}
