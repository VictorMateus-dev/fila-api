package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Repository.FilaRepository;
import br.com.sus.fila_api.Service.FilaService;
import br.com.sus.fila_api.model.Fila;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fila")
@RequiredArgsConstructor
public class FilaController {

    public final FilaService filaService;

    @PostMapping
    public Fila adiionar(@RequestParam String cpf,
                         @RequestParam Long exameid,
                         @RequestParam Long prioridadeId){

        return filaService.adicionarNaFila(cpf,exameid,prioridadeId);
    }

    @GetMapping
    public List<Fila> listarFila(){
        return filaService.listarTodos();
    }

    @GetMapping("/exame/{id}")
    public List<Fila> listarPorExame(@PathVariable Long id){
        return filaService.listarPorExame(id);
    }

    @PutMapping("/{id}/status")
    public Fila atualizarStatus(@PathVariable Long id,
                                @RequestParam String status) {

        return filaService.atualizarStatus(id, status);
    }

    @GetMapping("/{id}/posicao")
    public int posicao(@PathVariable Long id) {
        return filaService.calcularPosicao(id);
    }
}
