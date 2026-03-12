package com.agrolinq.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

    @Min(0)
    @Column(nullable = false)
    private Double precoUnitario;
}
