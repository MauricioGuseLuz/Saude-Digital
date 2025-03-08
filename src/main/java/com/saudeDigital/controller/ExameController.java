package com.saudeDigital.controller;


import com.saudeDigital.dtos.ExameDTO;
import com.saudeDigital.dtos.UsuarioDTO;
import com.saudeDigital.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
public class ExameController {

    private final ExameService exameService;

    @Autowired
    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @PostMapping()
    public ResponseEntity<ExameDTO> cadastrarExame(@RequestBody ExameDTO exameDTO) {
        ExameDTO exameCadastrado = exameService.cadastrarExame(exameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(exameCadastrado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExameDTO> buscarExamePorId(@PathVariable Long id) {
        ExameDTO exame = exameService.buscarExamePorId(id);
        return ResponseEntity.ok(exame);
    }
    @GetMapping()
    public ResponseEntity<List<ExameDTO>> listarExame (){
        List<ExameDTO> exames = exameService.listarExames();
        return ResponseEntity.ok(exames);
    }
    @PutMapping()
    public ResponseEntity<ExameDTO> atualizarExame(@RequestBody ExameDTO exameDTO) {
        return ResponseEntity.ok(exameService.atualizarExame(exameDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarExame(@RequestBody ExameDTO exameDTO) {
        exameService.deletarExame(exameDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
