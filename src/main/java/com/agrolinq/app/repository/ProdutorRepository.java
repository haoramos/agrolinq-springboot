package com.agrolinq.app.repository;

import com.agrolinq.app.models.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutorRepository extends JpaRepository<Produtor, UUID> {
    Optional<Produtor> findByEmail(String email);
    Optional<Produtor> findByCpf(String cpf);

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
