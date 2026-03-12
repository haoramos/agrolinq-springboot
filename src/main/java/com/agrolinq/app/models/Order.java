package com.agrolinq.app.models;

import com.agrolinq.app.models.enums.CanceladoPor;
import com.agrolinq.app.models.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "consumidor_id", nullable = false)
    private Consumidor consumidor;

    @ManyToOne
    @JoinColumn(name = "produtor_id", nullable = false)
    private Produtor produtor;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> itens;

    @Min(0)
    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Boolean produtorNotificado = false;


    // Avaliação
    private Integer avaliacaoNota;

    @Column(length = 500)
    private String avaliacaoComentario;

    private LocalDateTime avaliadoEm;


    // Cancelamento
    @Enumerated(EnumType.STRING)
    private CanceladoPor canceladoPor;

    private String motivoCancelamento;

    private LocalDateTime canceladoEm;


    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
