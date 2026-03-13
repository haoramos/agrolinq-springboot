package com.agrolinq.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderRequestDTO {

    @NotNull(message = "Produtor é obrigatório")
    private UUID produtorId;

    @NotEmpty(message = "O pedido deve ter pel menos um item")
    private List<OrderItemRequestDTO> itens;

}
