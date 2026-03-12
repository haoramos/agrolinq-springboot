package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "produtores")
public class Produtor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "E-mail é obrigatório.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "CPF é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senhaHash;

    // Detalhes da fazenda
    private String nomeFazenda;
    private String descricaoFazenda;
    private String localizacao;
    private String fotoFazenda;
    private String telefone;
    private String whatsapp;

    // Geolocalização
    private Double latitude;
    private Double longitude;

    // Selo Verde
    @Column(nullable = false)
    private Boolean seloVerde = false;

    private LocalDateTime seloVerdeAprovadoEm;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean ativo = true;
}
