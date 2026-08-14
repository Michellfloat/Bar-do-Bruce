package com.example.bar_do_bruce.controller;

import com.example.bar_do_bruce.model.EntregadorModel;
import com.example.bar_do_bruce.service.EntregadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    // Construtor manual para injeção de dependência sem depender do Lombok
    public EntregadorController(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;
    }

    @GetMapping
    public ResponseEntity<List<EntregadorModel>> listar() {
        return ResponseEntity.ok(entregadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorModel> buscarPorId(@PathVariable Long id) {
        return entregadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody EntregadorModel entregador) {
        try {
            EntregadorModel novoEntregador = entregadorService.salvar(entregador);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEntregador);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            entregadorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
