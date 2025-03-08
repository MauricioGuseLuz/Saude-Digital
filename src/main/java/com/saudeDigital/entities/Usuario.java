package com.saudeDigital.entities;

import com.saudeDigital.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String cpf;

    private LocalDate data_nascimento;

    private String telefone;


    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public boolean isMedico(){
        return tipoUsuario == TipoUsuario.MEDICO;
    }
}
