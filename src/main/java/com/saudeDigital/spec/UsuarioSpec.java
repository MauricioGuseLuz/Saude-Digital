package com.saudeDigital.spec;


import com.saudeDigital.dtos.UsuarioDTO;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioSpec {
    private static final String MSG_EMAIL = "Usuário já cadastrado com email: %s.";
    private static final String MSG_CPF = "Usuário já cadastrado com cpf: %s.";
    private static final String MSG_ID = "Id não pode ser nulo";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarSeExisteUsuarioComEmailDuplicado(Usuario usuario){
        if (nonNull(usuario)){
            throw new BussinesException(
                    String.format(MSG_EMAIL,usuario.getEmail()));
        }
    }
    public void verificarSeExisteUsuarioComCpfDuplicado(Usuario usuario) {
        if (nonNull(usuario)) {
            throw new BussinesException(
                    String.format(MSG_CPF, usuario.getEmail()));
        }
    }
    public void verificarSeCampoIdNulo(Long id){
        if (isNull(id)){
            String.format(MSG_ID, id);
        }
    }
    public void verificarSeEmailEmUso(Usuario usuario, UsuarioDTO usuarioDTO) {
        boolean alterouEmail = !(usuario.getEmail().equals(usuarioDTO.getEmail()));

        if (alterouEmail) {
            boolean encontrouUsuarioComEmailInformado = nonNull(usuarioRepository.findByEmail(usuarioDTO.getEmail()));

            if (encontrouUsuarioComEmailInformado) {
                throw new BussinesException(String.format(MSG_EMAIL, usuarioDTO.getEmail()));
            }
        }
    }
        public void verifacarSeCpfEmUso(Usuario usuario, UsuarioDTO usuarioDTO){
            if ((!(usuario.getCpf().equals(usuarioDTO.getCpf()))) && (nonNull(usuarioRepository.findByCpf(usuarioDTO.getCpf()))))
                throw new BussinesException(String.format(MSG_CPF, usuarioDTO.getCpf()));
        }

    }












