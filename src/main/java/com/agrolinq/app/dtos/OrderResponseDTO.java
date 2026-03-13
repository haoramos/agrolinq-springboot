package com.agrolinq.app.dtos;

import com.agrolinq.app.models.Consumidor;
import com.agrolinq.app.models.OrderItem;
import com.agrolinq.app.models.Produtor;
import com.agrolinq.app.models.enums.CanceladoPor;
import com.agrolinq.app.models.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderResponseDTO {

    private UUID id;
    private String consumidorId;
    private String consumidorNome;
    private String produtorId;
    private String produtorNome;
    private List<OrderItemResponseDTO> itens;
    private Double total;
    private OrderStatus status;
    private Boolean produtorNotificado = false;

    // Avaliação
    private Integer avaliacaoNota;
    private String avaliacaoComentario;
    private LocalDateTime avaliadoEm;

    // Cancelamento
    private CanceladoPor canceladoPor;
    private String motivoCancelamento;
    private LocalDateTime canceladoEm;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

}
