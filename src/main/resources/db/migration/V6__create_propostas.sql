CREATE TABLE propostas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    consumidor_id UUID REFERENCES consumidores(id) ON DELETE CASCADE,
    restaurante_id UUID REFERENCES restaurantes(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'SOLICITADA',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE proposta_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proposta_id UUID NOT NULL REFERENCES propostas(id) ON DELETE CASCADE,
    produto_id UUID NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
    nome VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL
);

CREATE TABLE proposta_respostas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    proposta_id UUID NOT NULL REFERENCES propostas(id) ON DELETE CASCADE,
    produtor_id UUID NOT NULL REFERENCES produtores(id) ON DELETE CASCADE,
    preco_total DOUBLE PRECISION NOT NULL,
    observacao TEXT,
    respondido_em TIMESTAMP NOT NULL DEFAULT NOW()
);