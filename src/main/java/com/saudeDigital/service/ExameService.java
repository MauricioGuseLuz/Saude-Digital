package com.saudeDigital.service;

import com.saudeDigital.dtos.ExameDTO;
import com.saudeDigital.entities.Exame;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.ExameRepository;
import com.saudeDigital.spec.ExameSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ExameService {

    private final ExameRepository exameRepository;

    @Autowired
    private ExameSpec exameSpec;

    @Autowired
    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;

    }

    public ExameDTO cadastrarExame(ExameDTO exameDTO) {


        Exame exame = converterExameDTO(exameDTO);
        exame = exameRepository.save(exame);
        return converterExame(exame);
    }

    public ExameDTO buscarExamePorId(Long id) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado!"));
        return converterExame(exame);
    }

    public List<ExameDTO> listarExames() {
        List<Exame> exames = exameRepository.findAll();
        return exames.stream()
                .map(this::converterExame)
                .collect(Collectors.toList());
    }

    public ExameDTO atualizarExame(ExameDTO exameDTO) {

        exameSpec.verificarSeCampoIdNulo(exameDTO.getId());

        Exame exame = exameRepository.findById(exameDTO.getId())
                .orElseThrow(() -> new BussinesException("MSG_ID_NULO"));

        // Verifica se o tipo está em uso por outro exame
        exameSpec.verificarSeTipoEmUso(exame, exameDTO);
        exameSpec.verificarSeCampoIdNulo(exameDTO.getId());

        if ((!(exame.getTipo().equals(exameDTO.getTipo()))) && (nonNull(exameRepository.findByTipo(exameDTO.getTipo()))))
            throw new BussinesException(String.format("Exame já cadastrado com o tipo: %s.", exameDTO.getTipo()));

        if((!(exame.getId().equals(exameDTO.getId()))) && (nonNull(exameRepository.findById(exameDTO.getId()))))
            throw new BussinesException(String.format("Exame já cadastrado com o ID: %s.", exameDTO.getId()));

        exameRepository.save(exame);
        return converterExame(exame);
    }

    public void deletarExame(Long id) {
        Exame exame = exameRepository.findById(id).orElseThrow();
        exameRepository.delete(exame);
    }

    private Exame converterExameDTO(ExameDTO exameDTO) {
        Exame exame = new Exame();
        exame.setId(exameDTO.getId());
        exame.setTipo(exameDTO.getTipo());
        exame.setDataExame(exameDTO.getDataExame());
        exame.setResultado(exameDTO.getResultado());
        exame.setArquivoResultado(exameDTO.getArquivoResultado());
        return exame;
    }

    private ExameDTO converterExame(Exame exame) {
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setId(exame.getId());
        exameDTO.setTipo(exame.getTipo());
        exameDTO.setDataExame(exame.getDataExame());
        exameDTO.setResultado(exame.getResultado());
        exameDTO.setArquivoResultado(exame.getArquivoResultado());
        return exameDTO;
    }
}



