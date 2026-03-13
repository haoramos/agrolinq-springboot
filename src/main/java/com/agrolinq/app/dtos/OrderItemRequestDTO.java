package com.agrolinq.app.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemRequestDTO {

    @NotBlank(message = "Produto é obrigatório")
    private UUID produtoId;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Min(value = 1, message = "Quantidade mínima é 1")
    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;

    @Min(value = 0, message = "Preço não pode ser negativo")
    @NotNull(message = "Preço unitário é obrigatório")
    private Double precoUnitario;

}
