package com.example.bar_do_bruce.service;

import com.example.bar_do_bruce.model.EntregadorModel;
import com.example.bar_do_bruce.repository.EntregadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public EntregadorService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public List<EntregadorModel> listarTodos() {
        return entregadorRepository.findAll();
    }

    public Optional<EntregadorModel> buscarPorId(Long id) {
        return entregadorRepository.findById(id);
    }

    public EntregadorModel salvar(EntregadorModel entregador) {
        if (entregadorRepository.findByNome(entregador.getNome()).isPresent()) {
            throw new RuntimeException("Este nome de entregador já está cadastrado.");
        }
        if (entregadorRepository.findByEmail(entregador.getEmail()).isPresent()) {
            throw new RuntimeException("Este e-mail já está sendo utilizado.");
        }
        return entregadorRepository.save(entregador);
    }

    public void deletar(Long id) {
        if (!entregadorRepository.existsById(id)) {
            throw new RuntimeException("Entregador não encontrado.");
        }
        entregadorRepository.deleteById(id);
    }
}
