package com.agrolinq.app.repository;

import com.agrolinq.app.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
    Optional<Restaurante> findByCnpj(String cnpj);
    Optional<Restaurante> findByEmail(String email);

    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
}
