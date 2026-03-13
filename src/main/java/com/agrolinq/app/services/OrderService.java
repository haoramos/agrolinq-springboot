package com.agrolinq.app.services;

import com.agrolinq.app.dtos.OrderRequestDTO;
import com.agrolinq.app.dtos.OrderResponseDTO;
import com.agrolinq.app.models.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO criar(OrderRequestDTO requestDTO, UUID consumidorId);
    OrderResponseDTO buscarPorId(UUID id);

    List<OrderResponseDTO> listarPorConsumidor(UUID consumidorId);
    List<OrderResponseDTO> listarPorProdutor(UUID produtorId);

    OrderResponseDTO autalizarStatus(UUID id, OrderStatus status, UUID produtorId);
    OrderResponseDTO cancelar(UUID id, UUID userId, String motivo);
    OrderResponseDTO avaliar(UUID id, Integer nota, String comentario, UUID consumidorId);
}
