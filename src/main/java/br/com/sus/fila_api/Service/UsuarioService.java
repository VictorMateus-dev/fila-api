package br.com.sus.fila_api.Service;

import br.com.sus.fila_api.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
}
