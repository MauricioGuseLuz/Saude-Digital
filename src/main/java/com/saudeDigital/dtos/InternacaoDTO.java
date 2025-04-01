package com.saudeDigital.dtos;


import lombok.Data;

@Data
public class InternacaoDTO {

    private Long id;

    private String gravidade;

    private String estado;

    private int numeroQuarto;

    private Long usuarioId;
}
