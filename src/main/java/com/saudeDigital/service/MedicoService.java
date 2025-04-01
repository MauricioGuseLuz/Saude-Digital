package com.saudeDigital.service;

import com.saudeDigital.dtos.MedicoDTO;
import com.saudeDigital.entities.Medico;
import com.saudeDigital.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository, UsuarioService usuarioService) {
        this.medicoRepository = medicoRepository;
        this.usuarioService = usuarioService;
    }

    public MedicoDTO cadastrarMedico(MedicoDTO medicoDTO) {
        Medico medico = converterMedicoDTO(medicoDTO);
        medico = medicoRepository.save(medico);
        return converterMedico(medico);
    }

    public MedicoDTO buscarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        return converterMedico(medico);
    }

    public List<MedicoDTO> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream()
                .map(this::converterMedico)
                .toList();
    }

    public MedicoDTO atualizarMedico(MedicoDTO medicoDTO) {
        if (!medicoRepository.existsById(medicoDTO.getId())) {
            throw new RuntimeException("Médico não encontrado!");
        }
        Medico medico = converterMedicoDTO(medicoDTO);
        medico = medicoRepository.save(medico);
        return converterMedico(medico);
    }

    public void deletarMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    private Medico converterMedicoDTO(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setId(medicoDTO.getId());
        medico.setNome(medicoDTO.getNome());
        medico.setCrm(medicoDTO.getCrm());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        return medico;
    }

    private MedicoDTO converterMedico(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setCrm(medico.getCrm());
        medicoDTO.setEspecialidade(medico.getEspecialidade());
        return medicoDTO;
    }
}