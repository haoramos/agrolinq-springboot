package com.agrolinq.app.repository;

import com.agrolinq.app.models.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsumidorRepository extends JpaRepository<Consumidor, UUID> {
    Optional<Consumidor> findByEmail(String email);

    Optional<Consumidor> findByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
