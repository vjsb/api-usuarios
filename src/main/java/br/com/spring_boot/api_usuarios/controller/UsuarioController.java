package br.com.spring_boot.api_usuarios.controller;

import br.com.spring_boot.api_usuarios.domain.Usuario;
import br.com.spring_boot.api_usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario criado = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(listaUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);

        if(usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarioOpt.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioParcial) {
        Usuario atualizado = usuarioService.atualizarUsuarioParcial(id, usuarioParcial);

        if(atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(atualizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(PathVariable id, @RequestBody Usuario usuarioAtualizado) {
        Usuario atualizado = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        if(atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
