package com.agrolinq.app.repository;

import com.agrolinq.app.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    List<Produto> findByProdutor_Id(UUID produtorId);

    List<Produto> findByCategoria(String categoria);
}