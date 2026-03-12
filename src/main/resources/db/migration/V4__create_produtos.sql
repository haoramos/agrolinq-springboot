CREATE TABLE produtos (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nome VARCHAR(255) NOT NULL,
  descricao TEXT NOT NULL,
  preco DOUBLE PRECISION NOT NULL,
  imagem_url VARCHAR(255),
  categoria VARCHAR(255) NOT NULL,
  produtor_id UUID NOT NULL REFERENCES produtores(id) ON DELETE CASCADE,
  estoque INTEGER NOT NULL DEFAULT 0,
  unidade VARCHAR(50) NOT NULL DEFAULT 'unid',
  descricao_detalhada TEXT,
  origem VARCHAR(255),
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE produto_certificacoes (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  produto_id UUID NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
  certificacao VARCHAR(255) NOT NULL
);

CREATE TABLE produto_imagens_adicionais (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  produto_id UUID NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
  imagem_url VARCHAR(255) NOT NULL
);

CREATE TABLE produto_especificacoes (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  produto_id UUID NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
  chave VARCHAR(255) NOT NULL,
  valor VARCHAR(255) NOT NULL
);