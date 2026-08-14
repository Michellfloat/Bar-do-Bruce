package com.example.bar_do_bruce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bar_do_bruce.dto.ClienteRequestDTO;
import com.example.bar_do_bruce.dto.ClienteResponseDTO;
import com.example.bar_do_bruce.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    ResponseEntity<List<ClienteResponseDTO>>listar(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarCliente());
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>>salvar(@Valid @RequestBody ClienteRequestDTO cliente){
        clienteService.salvarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cliente cadastrado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>>atualizar(@Valid @RequestBody ClienteRequestDTO cliente, @PathVariable Long id){
        clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Cliente atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>>deletar(@PathVariable Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Cliente deletado com sucesso"));
    }
}
