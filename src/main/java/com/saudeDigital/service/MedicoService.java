package com.saudeDigital.service;


import com.saudeDigital.dtos.MedicoDTO;
import com.saudeDigital.entities.Medico;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.exceptions.BussinesException;
import com.saudeDigital.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

@Service
public class MedicoService {

    @Autowired
    private final MedicoRepository medicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTO cadastrarMedico(MedicoDTO medicoDTO) {
        Medico medico = converterMedicoDTO(medicoDTO);
        medico = medicoRepository.save(medico);
        return converterMedico(medico);
    }
    public MedicoDTO buscarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico não encontrado!"));
        return converterMedico(medico);
    }
    public List<MedicoDTO> listarMedicos() {
        List<Medico> medico = medicoRepository.findAll();
        return medico.stream()
                .map(this::converterMedico)
                .toList();
    }
    public MedicoDTO atualizarMedico(MedicoDTO medicoDTO) {
        medicoRepository.findById(medicoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado!"));
        Medico medico = converterMedicoDTO(medicoDTO);
        medicoRepository.save(medico);
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

        if(nonNull(medicoDTO.getUsuarioId())){
            Usuario usuario = usuarioService.buscarUsuarioPorId(medicoDTO.getUsuarioId());
            if (!usuario.isMedico()){
               throw new BussinesException("Usuário não é médico!");
            }
            medico.setUsuario(usuario);
        }

        return medico;
    }
    private MedicoDTO converterMedico(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setCrm(medico.getCrm());
        return medicoDTO;
    }
}
