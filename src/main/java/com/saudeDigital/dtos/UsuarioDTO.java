package com.saudeDigital.dtos;



import com.saudeDigital.entities.Internacao;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioDTO {

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String cpf;

    private LocalDate data_nascimento;

    private String telefone;

    private List<InternacaoDTO> internacao;



}
