package br.com.sus.fila_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    private String cpf;

    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNacimento;
    @NotBlank
    private String telefone;

    private String email;
    @NotBlank
    private String endereço;

}
