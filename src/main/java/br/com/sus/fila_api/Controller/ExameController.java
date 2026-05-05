package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Service.ExameService;
import br.com.sus.fila_api.model.Exame;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExameController {

    public final ExameService exameService;

    @PostMapping
    public Exame salvar(@RequestBody Exame exame){
        return exameService.adicionarExame(exame);
    }

    @GetMapping
    public List<Exame> listar(){
        return exameService.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exame> buscarPorId(@PathVariable Long id){
        return exameService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        exameService.deletarExame(id);
                return ResponseEntity.noContent().build();
    }
}
