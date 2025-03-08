package com.saudeDigital.service;

import com.saudeDigital.dtos.UsuarioDTO;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.UsuarioRepository;
import com.saudeDigital.spec.UsuarioSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioSpec usuarioSpec;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuarioEmail = usuarioRepository
                .findByEmail(usuarioDTO.getEmail());

        usuarioSpec.verificarSeExisteUsuarioComEmailDuplicado(usuarioEmail);

        Usuario usuarioCpf = usuarioRepository
                .findByCpf(usuarioDTO.getCpf());

        usuarioSpec.verificarSeExisteUsuarioComCpfDuplicado(usuarioCpf);

        if (usuarioDTO.isMedico() && isNull(usuarioDTO.getMedico())){
            throw new BussinesException("Medico não informado!");
        }

        Usuario usuario = converterUsuarioDTO(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuario(usuario);
    }

    public UsuarioDTO buscarUsuarioDTOPorId(Long id) {
        return converterUsuario(buscarUsuarioPorId(id));
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BussinesException("MSG_USUARIO"));
        return usuario;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::converterUsuario)
                .collect(Collectors.toList());
    }


    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {

        usuarioSpec.verificarSeCampoIdNulo(usuarioDTO.getId());

        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() ->
                        new BussinesException("MSG_USUARIO "));

        usuarioSpec.verificarSeEmailEmUso(usuario, usuarioDTO);
        usuarioSpec.verifacarSeCpfEmUso(usuario, usuarioDTO);

        if ((!(usuario.getEmail().equals(usuarioDTO.getEmail()))) && (nonNull(usuarioRepository.findByEmail(usuarioDTO.getEmail()))))
            throw new BussinesException(String.format("Usuario ja cadastrado", usuarioDTO.getEmail()));

        if ((!(usuario.getCpf().equals(usuarioDTO.getCpf()))) && (nonNull(usuarioRepository.findByCpf(usuarioDTO.getCpf()))))
            throw new BussinesException(String.format("Usuario ja cadastrado com o cpf", usuarioDTO.getCpf()));

        usuario = converterUsuarioDTO(usuarioDTO);
        usuarioRepository.save(usuario);
        return converterUsuario(usuario);
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
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
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
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        return usuarioDTO;
    }
}