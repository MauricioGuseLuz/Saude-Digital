package com.saudeDigital.controller;

import com.saudeDigital.dtos.ConsultaDTO;
import com.saudeDigital.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping()
    public ResponseEntity<ConsultaDTO> cadastrarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO consultaCadastrado = consultaService.   cadastrarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarConsultaPorId(@PathVariable Long id) {
        ConsultaDTO consulta = consultaService.buscarConsultaPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping()
    public ResponseEntity<List<ConsultaDTO>> listarConsultas(){
        List<ConsultaDTO> consultas = consultaService.listarConsultas();
        return ResponseEntity.ok(consultas);
    }

    @PutMapping()
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.ok(consultaService.atualizarConsulta(consultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}