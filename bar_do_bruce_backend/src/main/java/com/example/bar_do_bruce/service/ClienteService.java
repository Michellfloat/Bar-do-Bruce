package com.example.bar_do_bruce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bar_do_bruce.dto.ClienteRequestDTO;
import com.example.bar_do_bruce.dto.ClienteResponseDTO;
import com.example.bar_do_bruce.model.enums.ClienteModel;
import com.example.bar_do_bruce.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public boolean isEmailUnique(String email) {
        return !clienteRepository.findByEmail(email).isPresent();
    }

    public ClienteModel salvarCliente(ClienteRequestDTO cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        ClienteModel novCliente = new ClienteModel();
        novCliente.setNome(cliente.getNome());
        novCliente.setEmail(cliente.getEmail());
        novCliente.setTelefone(cliente.getTelefone());
        return clienteRepository.save(novCliente);
    }

    public List<ClienteResponseDTO>listarCliente(){
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteResponseDTO(cliente.getNome(), cliente.getEmail(), cliente.getTelefone()))
                .toList();
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO cliente) {
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        if (!clienteExistente.getEmail().equals(cliente.getEmail()) &&
                clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());

        ClienteModel clienteAtualizado = clienteRepository.save(clienteExistente);
        return new ClienteResponseDTO(clienteAtualizado.getNome(), clienteAtualizado.getEmail(), clienteAtualizado.getTelefone());
    }

    @Transactional
    public void deletarCliente(Long id) {
        ClienteModel clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        clienteRepository.deleteById(clienteExistente.getId());
    }
}
