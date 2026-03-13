package com.agrolinq.app.controllers;

import com.agrolinq.app.dtos.*;
import com.agrolinq.app.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        return ResponseEntity.ok(authService.login(requestDTO));
    }

    @PostMapping("/register/consumidor")
    public ResponseEntity<LoginResponseDTO> registerConsumidor(@RequestBody @Valid ConsumidorRegisterRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerConsumidor(requestDTO));
    }

    @PostMapping("/register/produtor")
    public ResponseEntity<LoginResponseDTO> registerProdutor(@RequestBody @Valid ProdutorRegisterRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerProdutor(requestDTO));
    }

    @PostMapping("/register/restaurante")
    public ResponseEntity<LoginResponseDTO> registerRestaurante(@RequestBody @Valid RestauranteRegisterRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerRestaurante(requestDTO));
    }

}
