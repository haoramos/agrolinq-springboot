package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "proposta_items")
public class PropostaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

}
