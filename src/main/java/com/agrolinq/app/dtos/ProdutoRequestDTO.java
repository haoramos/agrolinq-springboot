package com.agrolinq.app.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
// What the client sends to the API
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição do produto é obrigatória")
    private String descricao;

    @Min(value = 0, message = "Preço não pode ser negativo")
    @NotNull(message = "Preço é obrigatório")
    private Double preco;

    private String imagemUrl;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer estoque = 0;

    private String unidade = "unid";

    private String descricaoDetalhada;
    private String origem;
    private List<String> certificacoes;
    private List<String> imagensAdicionais;
    private Map<String, String> especificacoes;

}
