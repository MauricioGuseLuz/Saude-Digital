package com.saudeDigital.dtos;


import com.saudeDigital.enums.TipoUsuario;
import lombok.Data;

@Data
public class MedicoDTO {

    private Long id;
    private String nome;
    private String crm;
    private Long especialidadeId;
    private Long usuarioId;
}
