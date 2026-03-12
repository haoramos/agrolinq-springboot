package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "proposta_respostas")
public class PropostaResposta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "proposta_id", nullable = false)
    private Proposta proposta;

    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    @Min(0)
    @Column(nullable = false)
    private Double precoTotal;

    private String observacao;

    @Column(nullable = false)
    private LocalDateTime respondidoEm = LocalDateTime.now();
}