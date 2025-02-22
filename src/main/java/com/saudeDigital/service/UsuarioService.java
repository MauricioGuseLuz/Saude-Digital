package com.saudeDigital.service;

import com.saudeDigital.dtos.UsuarioDTO;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterUsuarioDTO(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuario(usuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não foi encontrado."));
        return converterUsuario(usuario);
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        if (isNull(id)) {
            throw new BussinesException("Usuário não encontrado.");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BussinesException("Usuário não encontrado."));

        Usuario usuarioAtualizado = converterUsuarioDTO(usuarioDTO);
        usuarioAtualizado.setId(usuario.getId());
        usuarioRepository.save(usuarioAtualizado);

        return converterUsuario(usuarioAtualizado);
    }



    public void deletarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não foi encontrado."));


        usuarioRepository.delete(usuario);
    }


    private Usuario converterUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setData_nascimento(usuarioDTO.getData_nascimento());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTelefone(usuarioDTO.getTelefone());
        return usuario;
    }


    private UsuarioDTO converterUsuario(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setData_nascimento(usuario.getData_nascimento());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setTelefone(usuario.getTelefone());
        return usuarioDTO;
    }
}