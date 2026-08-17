package com.example.bar_do_bruce.repository;

import com.example.bar_do_bruce.model.EntregadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<EntregadorModel, Long> {
    Optional<EntregadorModel> findByNome(String nome);
    Optional<EntregadorModel> findByEmail(String email);
}
