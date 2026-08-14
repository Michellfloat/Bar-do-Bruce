package com.example.bar_do_bruce.repository;

import com.example.bar_do_bruce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoriaIgnoreCase(String categoria);
}