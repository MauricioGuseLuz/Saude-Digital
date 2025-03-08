package com.saudeDigital.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExameDTO {

    private Long id;
    private String tipo;
    private LocalDate dataExame;
    private String resultado;
    private String arquivoResultado;
    private Long consultaId;
}
