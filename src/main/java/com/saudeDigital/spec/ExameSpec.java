package com.saudeDigital.spec;

import com.saudeDigital.dtos.ExameDTO;
import com.saudeDigital.entities.Exame;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ExameSpec {

    private static final String MSG_TIPO_DUPLICADO = "Já existe um exame cadastrado com o tipo: %s.";
    private static final String MSG_ID_NULO = "O ID do exame não pode ser nulo.";
    private static final String MSG_EXAME_NAO_ENCONTRADO = "Exame não encontrado com o ID: %s.";

    @Autowired
    private ExameRepository exameRepository;


    /**
     * Verifica se o ID do exame é nulo.
     *
     * @param id
     */
    public void verificarSeCampoIdNulo(Long id) {
        if (isNull(id)) {
            throw new BussinesException(MSG_ID_NULO);
        }
    }

    /**
     * Verifica se o exame existe no banco de dados.
     *
     * @param id O ID do exame a ser verificado.
     */
    public void verificarSeExameExiste(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new BussinesException(String.format(MSG_EXAME_NAO_ENCONTRADO, id));
        }
    }

    /**
     * Verifica se o tipo do exame está em uso por outro exame.
     *
     * @param exame    O exame atual.
     * @param exameDTO O DTO com os novos dados do exame.
     */
    public void verificarSeTipoEmUso(Exame exame, ExameDTO exameDTO) {
        boolean alterouTipo = !(exame.getTipo().equals(exameDTO.getTipo()));

        if (alterouTipo) {
            boolean encontrouExameComTipoInformado = nonNull(exameRepository.findByTipo(exameDTO.getTipo()));

            if (encontrouExameComTipoInformado) {
                throw new BussinesException(String.format(MSG_TIPO_DUPLICADO, exameDTO.getTipo()));
            }
        }
    }
}