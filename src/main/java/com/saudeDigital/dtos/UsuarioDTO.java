package com.saudeDigital.dtos;


import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String cpf;

    private LocalDate data_nascimento;

    private String telefone;


}
