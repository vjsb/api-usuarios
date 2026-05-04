package br.com.spring_boot.api_usuarios.service.impl;

import br.com.spring_boot.api_usuarios.domain.Usuario;
import br.com.spring_boot.api_usuarios.repository.UsuarioRepository;
import br.com.spring_boot.api_usuarios.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado) {

        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isEmpty()) {
            return null;
        }

        Usuario usuarioExistente = usuarioExistenteOpt.get();

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());

        return usuarioRepository.save(usuarioExistente);
    }


    @Override
    public Usuario atualizarUsuarioParcial(Integer id, Usuario usuarioParcial) {

        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isEmpty()) {
            return null;
        }

        Usuario usuarioExistente = usuarioExistenteOpt.get();

        if (usuarioParcial.getNome() != null) {
            usuarioExistente.setNome(usuarioParcial.getNome());
        }

        if (usuarioParcial.getEmail() != null) {
            usuarioExistente.setEmail(usuarioParcial.getEmail());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
