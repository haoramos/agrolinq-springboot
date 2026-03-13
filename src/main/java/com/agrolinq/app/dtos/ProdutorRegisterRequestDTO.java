package com.agrolinq.app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutorRegisterRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
    private String senha;

    private String nomeFazenda;
    private String descricaoFazenda;
    private String localizacao;
    private String fotoFazenda;
    private String telefone;
    private String whatsapp;

}
