package com.agrolinq.app.dtos;

import com.agrolinq.app.models.Order;
import com.agrolinq.app.models.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderItemResponseDTO {

    private UUID id;
    private UUID produtoId;
    private String nome;
    private Integer quantidade;
    private Double precoUnitario;

}
