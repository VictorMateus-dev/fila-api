// PacienteController.java
package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Service.PacienteService;
import br.com.sus.fila_api.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.salvarPaciente(paciente);
        return ResponseEntity.status(201).body(salvo);   // 201 Created
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> buscarPorCpf(@PathVariable String cpf) {
        return pacienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        pacienteService.deletarPaciente(cpf);
        return ResponseEntity.noContent().build();
    }
}