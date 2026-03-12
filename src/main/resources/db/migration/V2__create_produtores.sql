CREATE TABLE produtores (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  nome_fazenda VARCHAR(255),
  descricao_fazenda TEXT,
  localizacao VARCHAR(255),
  foto_fazenda VARCHAR(255),
  telefone VARCHAR(20),
  whatsapp VARCHAR(20),
  latitude DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
  selo_verde BOOLEAN NOT NULL DEFAULT FALSE,
  selo_verde_aprovado_em TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);