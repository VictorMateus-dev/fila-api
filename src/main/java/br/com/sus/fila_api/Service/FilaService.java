package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.ExameRepository;
import br.com.sus.fila_api.Repository.FilaRepository;
import br.com.sus.fila_api.Repository.PacienteRepository;
import br.com.sus.fila_api.Repository.PrioridadeRepository;
import br.com.sus.fila_api.model.Exame;
import br.com.sus.fila_api.model.Fila;
import br.com.sus.fila_api.model.Paciente;
import br.com.sus.fila_api.model.Prioridade;
import jakarta.transaction.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilaService {

    public final FilaRepository filaRepository;
    public final PacienteRepository pacienteRepository;
    public final ExameRepository exameRepository;
    public final PrioridadeRepository prioridadeRepository;
    public final WhatsAppService whatsAppService;

    public Fila atualizarStatus(Long id, String novoStatus) {

        Fila fila = filaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fila não encontrada com id: " + id));

        String statusFormatado = novoStatus.toUpperCase();

        fila.setStatus(statusFormatado);
        fila.setDataAtualizacao(LocalDateTime.now());

        Fila filaSalva = filaRepository.save(fila);


        if (statusFormatado.equals("AGENDADO")) {

            String telefone = fila.getPaciente().getTelefone();

            String mensagem = "Olá " + fila.getPaciente().getNome() +
                    ", seu atendimento foi agendado! Esteja presente no Centro de Especialidades.";

            whatsAppService.enviarMensagem(telefone, mensagem);
        }

        return filaSalva;
    }

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
                .findByExameIdOrderByDataSolicitacaoAsc(exameId);
    }



    public int calcularPosicao(Long filaId) {

        Fila fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        List<Fila> lista = filaRepository
                .findByExameIdOrderByDataSolicitacaoAsc(
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
    public void deletarDaFila(Long id) {
        filaRepository.deleteById(id);
    }


}
