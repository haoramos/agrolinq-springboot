package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "E-mail é obrigatório.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "CNPJ é obrigatório.")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$",
            message = "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @Column(nullable = false)
    private String senhaHash;

    // Detalhes do restaurante

    private String nomeEstabelecimento;
    private String descricaoEstabelecimento;
    private String localizacao;
    private String fotoEstabelecimento;
    private String telefone;
    private String whatsapp;

    // Geolocalização
    private Double latitude;
    private Double longitude;

    @ElementCollection
    @CollectionTable(
            name = "restaurante_categorias_interesse",
            joinColumns = @JoinColumn(name = "restaurante_id")
    )
    @Column(name = "categoria")
    private List<String> categoriasProdutosInteresse;

    @Column(nullable = false)
    private Integer raioEntregaKm = 50;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean ativo = true;
}
