// src/main/java/com/agrolinq/app/models/Produto.java
package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome do produto é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória.")
    @Column(nullable = false)
    private String descricao;

    @Min(value = 0, message = "Preço não pode ser negativo.")
    @Column(nullable = false)
    private Double preco;

    private String imagemUrl;

    @NotBlank(message = "Categoria é obrigatória.")
    @Column(nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    @Column(nullable = false)
    private Integer estoque = 0;

    @Column(nullable = false)
    private String unidade = "unid";

    // Campos detalhados
    @Column(columnDefinition = "TEXT")
    private String descricaoDetalhada;

    private String origem;

    @ElementCollection
    @CollectionTable(
            name = "produto_certificacoes",
            joinColumns = @JoinColumn(name = "produto_id")
    )
    @Column(name = "certificacao")
    private List<String> certificacoes;

    @ElementCollection
    @CollectionTable(
            name = "produto_imagens_adicionais",
            joinColumns = @JoinColumn(name = "produto_id")
    )
    @Column(name = "imagem_url")
    private List<String> imagensAdicionais;

    @ElementCollection
    @CollectionTable(
            name = "produto_especificacoes",
            joinColumns = @JoinColumn(name = "produto_id")
    )
    @MapKeyColumn(name = "chave")
    @Column(name = "valor")
    private Map<String, String> especificacoes;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}