package com.saudeDigital.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "exames")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String tipo;

    private LocalDate dataExame;

    private String resultado;

    private String arquivoResultado;

}
