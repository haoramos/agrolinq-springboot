package com.agrolinq.app.services;

import com.agrolinq.app.dtos.ProdutoRequestDTO;
import com.agrolinq.app.dtos.ProdutoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoResponseDTO criar(ProdutoRequestDTO requestDTO, UUID produtorId);
    ProdutoResponseDTO atualizar(UUID id, ProdutoRequestDTO requestDTO, UUID produtorId);
    ProdutoResponseDTO buscarPorId(UUID id);

    List<ProdutoResponseDTO> listarPorProdutor(UUID produtorId);
    List<ProdutoResponseDTO> listarPorCategoria(String categoria);
    List<ProdutoResponseDTO> listarTodos();

    void deletar(UUID id, UUID produtorId);

}