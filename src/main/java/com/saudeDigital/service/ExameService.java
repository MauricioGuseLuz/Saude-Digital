package com.saudeDigital.service;

import com.saudeDigital.dtos.ExameDTO;
import com.saudeDigital.dtos.InternacaoDTO;
import com.saudeDigital.entities.Exame;
import com.saudeDigital.entities.Internacao;
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

    public final ExameRepository exameRepository;

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
        if (!exameRepository.existsById(exameDTO.getId())) {
            throw new RuntimeException("Exame não encontrado!");
        }


        Exame exame = converterExameDTO(exameDTO);
        exame = exameRepository.save(exame);
        return converterExame(exame);
    }

    public String deletarExame(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new RuntimeException("Exame não encontrado!");
        }

        exameRepository.deleteById(id);
        return "Exame deletado com sucesso!";
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



