package com.saudeDigital.dtos;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ConsultaDTO {

    private Long id;
    private LocalDateTime dataHora;
    private Long medicoId;
    private Long usuarioId;
}
