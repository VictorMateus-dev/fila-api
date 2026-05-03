package br.com.sus.fila_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;
    private LocalDateTime dataEnvio;
    private String statusEnvio;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cpf_paciente")
    private Paciente paciente;
}
