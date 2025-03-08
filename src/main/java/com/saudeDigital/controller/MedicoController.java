package com.saudeDigital.controller;


import com.saudeDigital.dtos.MedicoDTO;
import com.saudeDigital.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping()
    public ResponseEntity<MedicoDTO> cadastrarMedico(@RequestBody MedicoDTO medicoDTO){
        MedicoDTO medicoCadastrado = medicoService.cadastrarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCadastrado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscarMedicoPorId(@PathVariable Long id) {
        MedicoDTO medico = medicoService.buscarMedicoPorId(id);
        return ResponseEntity.ok(medico);
    }
    @GetMapping()
    public ResponseEntity <List<MedicoDTO>> listarMedicos(){
        List<MedicoDTO> medicos = medicoService.listarMedicos();
        return ResponseEntity.ok(medicos);
    }
    @PutMapping()
    public ResponseEntity<MedicoDTO> atualizarMedico(@RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.atualizarMedico(medicoDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarMedico(@RequestBody MedicoDTO medicoDTO) {
        medicoService.deletarMedico(medicoDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
