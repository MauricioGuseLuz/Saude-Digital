package com.saudeDigital.repositories;

import com.saudeDigital.entities.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {

    Exame findByTipo(String tipo);

}
