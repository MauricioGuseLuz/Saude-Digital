package com.saudeDigital.dtos;



import lombok.Data;

import java.util.List;

@Data
public class MedicoDTO {

    private Long id;
    private String nome;
    private String crm;
    private String especialidade;

    private List<UsuarioDTO> usuarios;
    private List<ConsultaDTO> consultas;


}

