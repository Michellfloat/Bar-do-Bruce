package com.example.bar_do_bruce.controller;

import com.example.bar_do_bruce.dto.FuncionarioDTO;
import com.example.bar_do_bruce.model.FuncionarioModel;
import com.example.bar_do_bruce.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<FuncionarioModel> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public FuncionarioModel criar(@RequestBody FuncionarioDTO dto) {
        return service.salvar(dto);
    }
}