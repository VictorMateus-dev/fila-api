package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.UsuarioRepository;
import br.com.sus.fila_api.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Username já existe");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!usuario.getPassword().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }
        return usuario;
    }
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
