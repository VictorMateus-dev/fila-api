package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {
    public final PacienteRepository pacienteRepository;


}
