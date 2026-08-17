package com.example.bar_do_bruce.service;

import com.example.bar_do_bruce.dto.FuncionarioDTO;
import com.example.bar_do_bruce.model.FuncionarioModel;
import com.example.bar_do_bruce.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<FuncionarioModel> listarTodos() {
        return repository.findAll();
    }

    public FuncionarioModel salvar(FuncionarioDTO dto) {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome(dto.getNome());
        funcionario.setMatricula(dto.getMatricula());
        funcionario.setCargo(dto.getCargo());

        return repository.save(funcionario);
    }
}