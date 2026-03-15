package com.agrolinq.app.config;

import com.agrolinq.app.models.Consumidor;
import com.agrolinq.app.models.Produtor;
import com.agrolinq.app.models.Restaurante;
import com.agrolinq.app.repository.ConsumidorRepository;
import com.agrolinq.app.repository.ProdutorRepository;
import com.agrolinq.app.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserHelper {

    private final ConsumidorRepository consumidorRepository;
    private final ProdutorRepository produtorRepository;
    private final RestauranteRepository restauranteRepository;

    public UUID getAuthenticatedUserId() {

        String email = getAuthenticatedEmail();

        Optional<Consumidor> consumidor = consumidorRepository.findByEmail(email);
        if (consumidor.isPresent()) return consumidor.get().getId();

        Optional<Produtor> produtor = produtorRepository.findByEmail(email);
        if (produtor.isPresent()) return produtor.get().getId();

        Optional<Restaurante> restaurante = restauranteRepository.findByEmail(email);
        if (restaurante.isPresent()) return restaurante.get().getId();

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado");

    }

    public String getAuthenticatedEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    public String getAuthenticatedTipo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", "").toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Tipo não encontrado."));
    }
}
