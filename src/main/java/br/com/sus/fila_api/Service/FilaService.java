package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.ExameRepository;
import br.com.sus.fila_api.Repository.FilaRepository;
import br.com.sus.fila_api.Repository.PacienteRepository;
import br.com.sus.fila_api.Repository.PrioridadeRepository;
import br.com.sus.fila_api.model.Exame;
import br.com.sus.fila_api.model.Fila;
import br.com.sus.fila_api.model.Paciente;
import br.com.sus.fila_api.model.Prioridade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilaService {

    public final FilaRepository filaRepository;
    public final PacienteRepository pacienteRepository;
    public final ExameRepository exameRepository;
    public final PrioridadeRepository prioridadeRepository;

    public Fila adicionarNaFila(String cpf,Long exame_id,Long prioridade_id){
        Paciente paciente = pacienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Exame exame = exameRepository.findById(exame_id)
                .orElseThrow(()-> new RuntimeException("Exame não encontrado"));

        Prioridade prioridade = prioridadeRepository.findById(prioridade_id)
                .orElseThrow(()-> new RuntimeException("Prioridade não encontrada"));

        Fila fila = new Fila();
        fila.setPaciente(paciente);
        fila.setExame(exame);
        fila.setPrioridade(prioridade);
        fila.setStatus("AGUARDANDO");
        fila.setDataSolicitacao(LocalDateTime.now());
        fila.setDataAtualizacao(LocalDateTime.now());

        return filaRepository.save(fila);
    }
    public List<Fila> listarPorExame(Long exameId) {

        return filaRepository
                .findByExameIdOrderByPrioridadeNivelDescDataSolicitacaoAsc(exameId);
    }


    public Fila atualizarStatus(Long filaId, String status) {

        Fila fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        fila.setStatus(status);
        fila.setDataAtualizacao(LocalDateTime.now());

        if (status.equals("AGENDADO")) {
            fila.setDataAgendamento(LocalDateTime.now());
        }

        return filaRepository.save(fila);
    }

    public int calcularPosicao(Long filaId) {

        Fila fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        List<Fila> lista = filaRepository
                .findByExameIdOrderByPrioridadeNivelDescDataSolicitacaoAsc(
                        fila.getExame().getId()
                );

        return lista.indexOf(fila) + 1;
    }

    public Fila buscarPorId(Long id) {
        return filaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fila não encontrada"));
    }

    public List<Fila> listarTodos() {
        return filaRepository.findAll();
    }
}
