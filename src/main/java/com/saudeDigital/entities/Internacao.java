package com.saudeDigital.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "internacao")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String situacao;

    private String estado;

    private int numeroQuarto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;


}
