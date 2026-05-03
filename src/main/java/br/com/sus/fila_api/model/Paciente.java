package br.com.sus.fila_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    private String cpf;

    private String nome;
    private LocalDate dataNacimento;
    private String telefone;
    private String email;
    private String endereço;

}
