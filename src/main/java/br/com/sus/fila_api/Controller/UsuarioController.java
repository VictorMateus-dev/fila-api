package br.com.sus.fila_api.Controller;

import br.com.sus.fila_api.Service.UsuarioService;
import br.com.sus.fila_api.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestParam String username,
                         @RequestParam String senha) {

        return usuarioService.login(username, senha);
    }

}
