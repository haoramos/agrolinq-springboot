CREATE TABLE selo_verde (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    produtor_id UUID NOT NULL UNIQUE REFERENCES produtores(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    descricao_praticas VARCHAR(1000) NOT NULL,
    avaliado_por UUID REFERENCES consumidores(id),
    motivo_rejeicao TEXT,
    avaliado_em TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE selo_verde_documentos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    selo_verde_id UUID NOT NULL REFERENCES selo_verde(id) ON DELETE CASCADE,
    documento_url VARCHAR(255) NOT NULL
);

CREATE TABLE selo_verde_fotos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    selo_verde_id UUID NOT NULL REFERENCES selo_verde(id) ON DELETE CASCADE,
    foto_url VARCHAR(255) NOT NULL
);