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
public class PacienteController {

    public final PacienteService pacienteService;

    @PostMapping
    public Paciente paciente(@RequestBody Paciente paciente){
        return pacienteService.salvarPaciente(paciente);
    }
    @GetMapping
    public List<Paciente> listar(){
        return pacienteService.listarPacientes();
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> buscarPorCpf(@PathVariable String cpf){
        return pacienteService.buscarPorCpf(cpf)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
