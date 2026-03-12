package com.agrolinq.app.repository;

import com.agrolinq.app.models.SeloVerde;
import com.agrolinq.app.models.enums.SeloVerdeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SeloVerdeRepository extends JpaRepository<SeloVerde, UUID> {
    Optional<SeloVerde> findByProdutor_Id(UUID produtorId);

    Optional<SeloVerde> findByStatus(SeloVerdeStatus status);

    boolean existsByProdutor_Id(UUID produtorId);
}
