package com.agrolinq.app.services;

import com.agrolinq.app.dtos.*;

public interface AuthService {

    // after any registration or login, the user should receive a token immediately
    LoginResponseDTO login(LoginRequestDTO requestDTO);
    LoginResponseDTO registerConsumidor(ConsumidorRegisterRequestDTO requestDTO);
    LoginResponseDTO registerProdutor(ProdutorRegisterRequestDTO requestDTO);
    LoginResponseDTO registerRestaurante(RestauranteRegisterRequestDTO requestDTO);

}
