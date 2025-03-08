package com.saudeDigital.controller;


import com.saudeDigital.dtos.EspecialidadeDTO;
import com.saudeDigital.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @Autowired
    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }
    @PostMapping()
    public ResponseEntity<EspecialidadeDTO>cadastrarEspecialidade(@RequestBody EspecialidadeDTO especialidadeDTO){
        EspecialidadeDTO especialidadeCadastrado = especialidadeService.cadastrarEspecialidade(especialidadeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeCadastrado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> buscarEspecialidadePorId(@PathVariable Long id) {
        EspecialidadeDTO especialidade = especialidadeService.buscarEspecialidadePorId(id);
        return ResponseEntity.ok(especialidade);
    }
    @GetMapping()
    public ResponseEntity<List<EspecialidadeDTO>> listarEspecialidades(){
        List<EspecialidadeDTO> especialidades = especialidadeService.listarEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

}



