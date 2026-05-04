package br.com.spring_boot.api_usuarios.service;

import br.com.spring_boot.api_usuarios.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario criarUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    Optional<Usuario> buscarPorId(Integer id);

    Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado);

    Usuario atualizarUsuarioParcial(Integer id, Usuario usuarioParcial);

    void deletarUsuario(Integer id);
}
