package com.agrolinq.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private String id;
    private String nome;
    private String email;
    private String tipo;

}
