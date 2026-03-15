package com.agrolinq.app.controllers;

import com.agrolinq.app.config.AuthenticatedUserHelper;
import com.agrolinq.app.dtos.OrderRequestDTO;
import com.agrolinq.app.dtos.OrderResponseDTO;
import com.agrolinq.app.models.enums.OrderStatus;
import com.agrolinq.app.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthenticatedUserHelper authenticatedUserHelper;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> criar(
            @RequestBody @Valid OrderRequestDTO requestDTO
    ) {
        UUID consumidorId = authenticatedUserHelper.getAuthenticatedUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.criar(requestDTO, consumidorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> buscarPorId(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(orderService.buscarPorId(id));
    }

    @GetMapping("/consumidor/{consumidorId}")
    public ResponseEntity<List<OrderResponseDTO>> listarPorConsumidor(
            @PathVariable UUID consumidorId
    ) {
        return ResponseEntity.ok(orderService.listarPorConsumidor(consumidorId));
    }

    @GetMapping("/produtor/{produtorId}")
    public ResponseEntity<List<OrderResponseDTO>> listarPorProdutor(
            @PathVariable UUID produtorId
    ) {
        return ResponseEntity.ok(orderService.listarPorProdutor(produtorId));
    }

    @PutMapping("{id}/status")
    public ResponseEntity<OrderResponseDTO> atualizarStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body
    ) {
        UUID produtorId = authenticatedUserHelper.getAuthenticatedUserId();
        OrderStatus status = OrderStatus.valueOf(body.get("status").toUpperCase());
        return ResponseEntity.ok(orderService.autalizarStatus(id, status, produtorId));
    }

    @PatchMapping("{id}/cancelar")
    public ResponseEntity<OrderResponseDTO> cancelar(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body
    ) {
        UUID userId = authenticatedUserHelper.getAuthenticatedUserId();
        return ResponseEntity.ok(orderService.cancelar(id, userId, body.get("motivo")));
    }

    @PatchMapping("{id}/avaliar")
    public ResponseEntity<OrderResponseDTO> avaliar(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> body
    ) {
        UUID consumidorId = authenticatedUserHelper.getAuthenticatedUserId();
        Integer nota = (Integer) body.get("nota");
        String comentario = (String) body.get("comentario");
        return ResponseEntity.ok(orderService.avaliar(id, nota, comentario, consumidorId));
    }
}