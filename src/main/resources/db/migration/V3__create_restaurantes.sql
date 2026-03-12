CREATE TABLE restaurantes (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  cnpj VARCHAR(18) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  nome_estabelecimento VARCHAR(255),
  descricao_estabelecimento TEXT,
  localizacao VARCHAR(255),
  foto_estabelecimento VARCHAR(255),
  telefone VARCHAR(20),
  whatsapp VARCHAR(20),
  latitude DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
  raio_entrega_km INTEGER NOT NULL DEFAULT 50,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE restaurante_categorias_interesse (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  restaurante_id UUID NOT NULL REFERENCES restaurantes(id) ON DELETE CASCADE,
  categoria VARCHAR(255) NOT NULL
);