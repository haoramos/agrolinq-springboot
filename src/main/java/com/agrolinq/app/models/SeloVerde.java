package com.agrolinq.app.models;

import com.agrolinq.app.models.enums.SeloVerdeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "selo_verde")
public class SeloVerde {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produtor_id")
    private Produtor produtor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeloVerdeStatus status =  SeloVerdeStatus.PENDENTE;

    @ElementCollection
    @CollectionTable(
            name = "selo_verde_documentos",
            joinColumns = @JoinColumn(name = "selo_verde_id")
    )
    @Column(name = "documento_url")
    private List<String> documentos;

    @Size(min = 50, max = 1000, message = "Descrição das práticas sustentáveis deve ter entre 50 e 1000 caracteres.")
    @NotBlank(message = "Descrição das práticas sustentáveis é obrigatória.")
    @Column(nullable = false, length = 1000)
    private String descricaoPraticas;

    @ElementCollection
    @CollectionTable(
            name = "selo_verde_fotos",
            joinColumns = @JoinColumn(name = "selo_verde_id")
    )
    @Column(name = "foto_url")
    private List<String> fotos;

//---------------------------------
    // Avaliação
    @ManyToOne
    @JoinColumn(name = "avaliado_por")
    private Consumidor avaliadoPor;

    private String motivoRejeicao;
    private LocalDateTime avaliadoEm;
//---------------------------------

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
