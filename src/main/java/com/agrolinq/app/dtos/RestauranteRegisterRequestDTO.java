package com.agrolinq.app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RestauranteRegisterRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$",
            message = "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
    private String senha;

    private String nomeEstabelecimento;
    private String descricaoEstabelecimento;
    private String localizacao;
    private String fotoEstabelecimento;
    private String telefone;
    private String whatsapp;

}
