package com.saudeDigital.controller;


import com.saudeDigital.dtos.InternacaoDTO;
import com.saudeDigital.service.InternacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internacao")
public class InternacaoController {

    private final InternacaoService internacaoService;

    @Autowired
    public InternacaoController(InternacaoService internacaoService) {
        this.internacaoService = internacaoService;
    }

    @PostMapping()
    public ResponseEntity<InternacaoDTO> cadastrarInternacao(@RequestBody InternacaoDTO internacaoDTO){
        InternacaoDTO internacaoCadastrado = internacaoService.cadastrarInternacao(internacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(internacaoCadastrado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InternacaoDTO> buscarInternacaoPorId(@PathVariable Long id) {
        InternacaoDTO internacao = internacaoService.buscarInternacaoPorId(id);
        return ResponseEntity.ok(internacao);
    }
    @GetMapping()
    public ResponseEntity <List<InternacaoDTO>> listarInternacaos(){
        List<InternacaoDTO> internacaos = internacaoService.listarInternacoes();
        return ResponseEntity.ok(internacaos);
    }
    @PutMapping()
    public ResponseEntity<InternacaoDTO> atualizarInternacao(@RequestBody InternacaoDTO internacaoDTO) {
        return ResponseEntity.ok(internacaoService.atualizarInternacao(internacaoDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarInternacao(@PathVariable Long id) {
        if (!internacaoService.internacaoRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Internação não encontrada!");
        }

        internacaoService.deletarInternacao(id);
        return ResponseEntity.ok("Internação deletada com sucesso!");
    }
}
