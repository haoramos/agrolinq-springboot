package com.agrolinq.app.repository;

import com.agrolinq.app.models.Proposta;
import com.agrolinq.app.models.enums.PropostaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {
    Optional<Proposta> findByConsumidor_Id(UUID consumidorId);

    Optional<Proposta> findByRestaurante_Id(UUID restauranteId);

    Optional<Proposta> findByStatus(PropostaStatus status);
}
