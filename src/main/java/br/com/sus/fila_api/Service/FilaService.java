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

    private final FilaRepository filaRepository;
    private final PacienteRepository pacienteRepository;
    private final ExameRepository exameRepository;
    private final PrioridadeRepository prioridadeRepository;
    private final WhatsappService whatsAppService;

    public Fila atualizarStatus(Long id, String novoStatus) {

        Fila fila = filaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fila não encontrada com id: " + id));

        String statusFormatado = novoStatus.toUpperCase().trim();

        fila.setStatus(statusFormatado);
        fila.setDataAtualizacao(LocalDateTime.now());

        Fila filaSalva = filaRepository.save(fila);


        if ("AGENDADO".equals(statusFormatado)) {
            try {
                String telefone = fila.getPaciente().getTelefone();
                String nomePaciente = fila.getPaciente().getNome();

                String mensagem = "Olá " + nomePaciente + " 👋\n\n" +
                        "Seu atendimento foi **agendado** com sucesso!\n\n" +
                        "📍 Esteja presente no Centro de Especialidades, no dia 20/05/2026";

                System.out.println("📲 Enviando WhatsApp para: " + telefone);
                whatsAppService.enviarMensagem(telefone, mensagem);

            } catch (Exception e) {
                System.err.println("⚠️ Erro ao enviar mensagem WhatsApp: " + e.getMessage());

            }
        }

        return filaSalva;
    }

    public Fila adicionarNaFila(String cpf, Long exame_id, Long prioridade_id) {
        Paciente paciente = pacienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com CPF: " + cpf));

        Exame exame = exameRepository.findById(exame_id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado"));

        Prioridade prioridade = prioridadeRepository.findById(prioridade_id)
                .orElseThrow(() -> new RuntimeException("Prioridade não encontrada"));

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
        return filaRepository.findByExameIdOrderByDataSolicitacaoAsc(exameId);
    }

    public int calcularPosicao(Long filaId) {
        Fila fila = filaRepository.findById(filaId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        List<Fila> lista = filaRepository
                .findByExameIdOrderByDataSolicitacaoAsc(fila.getExame().getId());

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