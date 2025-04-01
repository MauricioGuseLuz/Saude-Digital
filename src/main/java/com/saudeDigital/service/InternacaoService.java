package com.saudeDigital.service;

import com.saudeDigital.dtos.InternacaoDTO;
import com.saudeDigital.entities.Internacao;
import com.saudeDigital.entities.Usuario;
import com.saudeDigital.repositories.InternacaoRepository;
import com.saudeDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternacaoService {

    public final InternacaoRepository internacaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public InternacaoService(InternacaoRepository internacaoRepository, UsuarioRepository usuarioRepository) {
        this.internacaoRepository = internacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public InternacaoDTO cadastrarInternacao(InternacaoDTO internacaoDTO) {
        if (internacaoDTO.getUsuarioId() == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }


        Usuario usuario = usuarioRepository.findById(internacaoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));


        Internacao internacao = new Internacao();
        internacao.setSituacao(internacaoDTO.getSituacao());
        internacao.setEstado(internacaoDTO.getEstado());
        internacao.setNumeroQuarto(internacaoDTO.getNumeroQuarto());
        internacao.setUsuario(usuario);

        internacao = internacaoRepository.save(internacao);

        return converterInternacao(internacao);
    }

    public InternacaoDTO buscarInternacaoPorId(Long id) {
        Internacao internacao = internacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internação não encontrada!"));
        return converterInternacao(internacao);
    }

    public List<InternacaoDTO> listarInternacoes() {
        List<Internacao> internacoes = internacaoRepository.findAll();
        return internacoes.stream()
                .map(this::converterInternacao)
                .toList();
    }

    public InternacaoDTO atualizarInternacao(InternacaoDTO internacaoDTO) {
        if (!internacaoRepository.existsById(internacaoDTO.getId())) {
            throw new RuntimeException("Internação não encontrada!");
        }


        Internacao internacao = converterInternacaoDTO(internacaoDTO);
        internacao = internacaoRepository.save(internacao);
        return converterInternacao(internacao);
    }

    public String deletarInternacao(Long id) {
        if (!internacaoRepository.existsById(id)) {
            throw new RuntimeException("Internação não encontrada!");
        }

        internacaoRepository.deleteById(id);
        return "Médico deletado com sucesso!";
    }

    private Internacao converterInternacaoDTO(InternacaoDTO internacaoDTO) {
        Internacao internacao = new Internacao();
        internacao.setId(internacaoDTO.getId());
        internacao.setSituacao(internacaoDTO.getSituacao());
        internacao.setEstado(internacaoDTO.getEstado());
        internacao.setNumeroQuarto(internacaoDTO.getNumeroQuarto());
        return internacao;
    }

    private InternacaoDTO converterInternacao(Internacao internacao) {
        InternacaoDTO internacaoDTO = new InternacaoDTO();
        internacaoDTO.setId(internacao.getId());
        internacaoDTO.setSituacao(internacao.getSituacao());
        internacaoDTO.setEstado(internacao.getEstado());
        internacaoDTO.setNumeroQuarto(internacao.getNumeroQuarto());
        return internacaoDTO;
    }
}
