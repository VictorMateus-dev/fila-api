package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.PacienteRepository;
import br.com.sus.fila_api.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {
    public final PacienteRepository pacienteRepository;


    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorCpf(String cpf){
        return pacienteRepository.findById(cpf);

    }

    public Paciente salvarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(String cpf) {
        pacienteRepository.deleteById(cpf);
    }
}
