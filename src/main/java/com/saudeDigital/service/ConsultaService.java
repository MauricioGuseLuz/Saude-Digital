package com.saudeDigital.service;

import com.saudeDigital.dtos.ConsultaDTO;
import com.saudeDigital.entities.Consulta;
import com.saudeDigital.entities.Medico;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.repositories.ConsultaRepository;
import com.saudeDigital.repositories.MedicoRepository;
import com.saudeDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository,
                           UsuarioRepository usuarioRepository,
                           MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
    }

    public ConsultaDTO cadastrarConsulta(ConsultaDTO consultaDTO) {
        if (consultaDTO.getMedicoId() == null || consultaDTO.getUsuarioId() == null) {
            throw new IllegalArgumentException("Os IDs do médico e do usuário não podem ser nulos.");
        }

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        Usuario usuario = usuarioRepository.findById(consultaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setMedico(medico);
        consulta.setUsuario(usuario);

        consulta = consultaRepository.save(consulta);

        return converterParaConsultaDTO(consulta);
    }

    public ConsultaDTO buscarConsultaPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
        return converterParaConsultaDTO(consulta);
    }

    public ConsultaDTO atualizarConsulta(ConsultaDTO consultaDTO) {
        if (consultaDTO.getMedicoId() == null || consultaDTO.getUsuarioId() == null) {
            throw new IllegalArgumentException("Os IDs do médico e do usuário não podem ser nulos.");
        }

        Consulta consulta = consultaRepository.findById(consultaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));

        Usuario usuario = usuarioRepository.findById(consultaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));

        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setUsuario(usuario);
        consulta.setMedico(medico);

        consultaRepository.save(consulta);
        return converterParaConsultaDTO(consulta);
    }

    public void deletarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    public List<ConsultaDTO> listarConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(this::converterParaConsultaDTO)
                .toList();
    }

    public ConsultaDTO converterParaConsultaDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setDataHora(consulta.getDataHora());
        consultaDTO.setMedicoId(consulta.getMedico().getId());
        consultaDTO.setUsuarioId(consulta.getUsuario().getId());
        return consultaDTO;
    }

    public Consulta converterConsulta(ConsultaDTO consultaDTO) {
        if (consultaDTO.getMedicoId() == null || consultaDTO.getUsuarioId() == null) {
            throw new IllegalArgumentException("Os IDs do médico e do usuário não podem ser nulos.");
        }

        Medico medico = medicoRepository.findById(consultaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        Usuario usuario = usuarioRepository.findById(consultaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setMedico(medico);
        consulta.setUsuario(usuario);

        return consulta;
    }
}
