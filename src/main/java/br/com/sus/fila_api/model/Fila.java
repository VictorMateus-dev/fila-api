package br.com.sus.fila_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAtualizacao;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cpf_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "exame_id")
    private exame exame;

    @ManyToOne
    @JoinColumn(name = "prioridade_id")
    private Prioridade prioridade;
}
