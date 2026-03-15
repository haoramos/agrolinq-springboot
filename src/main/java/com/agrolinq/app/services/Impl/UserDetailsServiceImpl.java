package com.agrolinq.app.services.Impl;

import com.agrolinq.app.models.Consumidor;
import com.agrolinq.app.models.Produtor;
import com.agrolinq.app.models.Restaurante;
import com.agrolinq.app.repository.ConsumidorRepository;
import com.agrolinq.app.repository.ProdutorRepository;
import com.agrolinq.app.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ConsumidorRepository consumidorRepository;
    private final ProdutorRepository produtorRepository;
    private final RestauranteRepository restauranteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Restaurante> restaurante = restauranteRepository.findByEmail(email);
        if (restaurante.isPresent()) {
            return new User(
                    restaurante.get().getEmail(),
                    restaurante.get().getSenhaHash(),
                    List.of(new SimpleGrantedAuthority("ROLE_RESTAURANTE"))
            );
        }

        Optional<Produtor> produtor = produtorRepository.findByEmail(email);
        if (produtor.isPresent()) {
            return new User(
                    produtor.get().getEmail(),
                    produtor.get().getSenhaHash(),
                    List.of(new SimpleGrantedAuthority("ROLE_PRODUTOR"))
            );
        }

        Optional<Consumidor> consumidor = consumidorRepository.findByEmail(email);
        if (consumidor.isPresent()) {
            return new User(
                    consumidor.get().getEmail(),
                    consumidor.get().getSenhaHash(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + consumidor.get().getTipo().name()))
            );
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + email);
    }
}