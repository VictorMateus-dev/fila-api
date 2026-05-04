package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Service.PrioridadeService;
import br.com.sus.fila_api.model.Prioridade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prioridades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PrioridadeController {

    private final PrioridadeService prioridadeService;

    @GetMapping
    public List<Prioridade> listar() {
        return prioridadeService.listarPrioridade();
    }
}