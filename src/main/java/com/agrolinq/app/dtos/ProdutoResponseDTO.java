package com.agrolinq.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
// What the API sends to the costumer, what the frontend needs to display
public class ProdutoResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;
    private String categoria;
    private String produtorId;
    private String produtorNome;
    private Integer estoque;
    private String unidade;
    private String descricaoDetalhada;
    private String origem;
    private List<String> certificacoes;
    private List<String> imagensAdicionais;
    private Map<String, String> especificacoes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
