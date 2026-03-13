package com.agrolinq.app.services.Impl;

import com.agrolinq.app.dtos.*;
import com.agrolinq.app.models.Consumidor;
import com.agrolinq.app.models.Produtor;
import com.agrolinq.app.models.Restaurante;
import com.agrolinq.app.repository.ConsumidorRepository;
import com.agrolinq.app.repository.ProdutorRepository;
import com.agrolinq.app.repository.RestauranteRepository;
import com.agrolinq.app.services.AuthService;
import com.agrolinq.app.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ConsumidorRepository consumidorRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProdutorRepository produtorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        var restaurante = restauranteRepository.findByEmail(requestDTO.getEmail());
        if (restaurante.isPresent()) {
            return authenticate(restaurante.get().getSenhaHash(),
                                requestDTO.getSenha(),
                                restaurante.get().getId().toString(),
                                restaurante.get().getNome(),
                                restaurante.get().getEmail(),
                                "restaurante");
        }

        var produtor = produtorRepository.findByEmail(requestDTO.getEmail());
        if (produtor.isPresent()) {
            return authenticate(produtor.get().getSenhaHash(),
                                requestDTO.getSenha(),
                                produtor.get().getId().toString(),
                                produtor.get().getNome(),
                                produtor.get().getEmail(),
                            "produtor");
        }

        var consumidor = consumidorRepository.findByEmail(requestDTO.getEmail());
        if (consumidor.isPresent()) {
            return authenticate(consumidor.get().getSenhaHash(),
                                requestDTO.getSenha(),
                                consumidor.get().getId().toString(),
                                consumidor.get().getNome(),
                                consumidor.get().getEmail(),
                                consumidor.get().getTipo().name().toLowerCase());
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos.");
    }

    @Override
    public LoginResponseDTO registerConsumidor(ConsumidorRegisterRequestDTO requestDTO) {
        if (consumidorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }

        if (consumidorRepository.existsByCpf(requestDTO.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado.");
        }

        Consumidor consumidor = new Consumidor();
        consumidor.setNome(requestDTO.getNome());
        consumidor.setEmail(requestDTO.getEmail().toLowerCase());
        consumidor.setCpf(requestDTO.getCpf());
        consumidor.setSenhaHash(passwordEncoder.encode(requestDTO.getSenha()));

        consumidorRepository.save(consumidor);

        String token = jwtService.generateToken(consumidor.getEmail(), "consumidor");
        return new LoginResponseDTO(token,
                                    consumidor.getId().toString(),
                                    consumidor.getNome(),
                                    consumidor.getEmail(),
                                    "consumidor");
    }

    @Override
    public LoginResponseDTO registerProdutor(ProdutorRegisterRequestDTO requestDTO) {
        if (produtorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }

        if (produtorRepository.existsByCpf(requestDTO.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado.");
        }

        Produtor produtor = new Produtor();
        produtor.setNome(requestDTO.getNome());
        produtor.setEmail(requestDTO.getEmail().toLowerCase());
        produtor.setCpf(requestDTO.getCpf());
        produtor.setSenhaHash(passwordEncoder.encode(requestDTO.getSenha()));
        produtor.setNomeFazenda(requestDTO.getNomeFazenda());
        produtor.setDescricaoFazenda(requestDTO.getDescricaoFazenda());
        produtor.setLocalizacao(requestDTO.getLocalizacao());
        produtor.setTelefone(requestDTO.getTelefone());
        produtor.setWhatsapp(requestDTO.getWhatsapp());

        produtorRepository.save(produtor);

        String token = jwtService.generateToken(produtor.getEmail(), "produtor");
        return new LoginResponseDTO(token,
                                    produtor.getId().toString(),
                                    produtor.getNome(),
                                    produtor.getEmail(),
                                    "produtor");
    }

    @Override
    public LoginResponseDTO registerRestaurante(RestauranteRegisterRequestDTO requestDTO) {
        if (restauranteRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }

        if (restauranteRepository.existsByCnpj(requestDTO.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado.");
        }

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(requestDTO.getNome());
        restaurante.setEmail(requestDTO.getEmail().toLowerCase());
        restaurante.setCnpj(requestDTO.getCnpj());
        restaurante.setSenhaHash(passwordEncoder.encode(requestDTO.getSenha()));
        restaurante.setNomeEstabelecimento(requestDTO.getNomeEstabelecimento());
        restaurante.setDescricaoEstabelecimento(requestDTO.getDescricaoEstabelecimento());
        restaurante.setLocalizacao(requestDTO.getLocalizacao());
        restaurante.setTelefone(requestDTO.getTelefone());
        restaurante.setWhatsapp(requestDTO.getWhatsapp());

        restauranteRepository.save(restaurante);

        String token = jwtService.generateToken(restaurante.getEmail(), "restaurante");
        return new LoginResponseDTO(token,
                                    restaurante.getId().toString(),
                                    restaurante.getNome(),
                                    restaurante.getEmail(),
                                    "restaurante");
    }

    // Private helper to verify password and return token
    private LoginResponseDTO authenticate(String senhaHash, String senhaInput,
                                          String id, String nome,
                                          String email, String tipo) {
        if (!passwordEncoder.matches(senhaInput, senhaHash)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos.");
        }
        String token = jwtService.generateToken(email, tipo);
        return new LoginResponseDTO(token, id, nome, email, tipo);
    }
}
