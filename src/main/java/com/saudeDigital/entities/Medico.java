package com.saudeDigital.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String crm;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Coluna NOT NULL
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false) // Coluna NOT NULL
    private Especialidade especialidade;

}
