package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Service.FilaService;
import br.com.sus.fila_api.model.Fila;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fila")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FilaController {

    private final FilaService filaService;


    @PostMapping
    public ResponseEntity<Fila> adicionar(
            @RequestParam String cpf,
            @RequestParam Long exameId,
            @RequestParam Long prioridadeId) {

        Fila fila = filaService.adicionarNaFila(cpf, exameId, prioridadeId);
        return ResponseEntity.ok(fila);
    }

    @GetMapping
    public ResponseEntity<List<Fila>> listarFila() {
        return ResponseEntity.ok(filaService.listarTodos());
    }

    @GetMapping("/exame/{id}")
    public ResponseEntity<List<Fila>> listarPorExame(@PathVariable Long id) {
        return ResponseEntity.ok(filaService.listarPorExame(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Fila> atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Fila filaAtualizada = filaService.atualizarStatus(id, status);
        return ResponseEntity.ok(filaAtualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDaFila(@PathVariable Long id) {
        filaService.deletarDaFila(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posicao")
    public ResponseEntity<Integer> posicao(@PathVariable Long id) {
        return ResponseEntity.ok(filaService.calcularPosicao(id));
    }
}