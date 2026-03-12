CREATE TABLE orders (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  consumidor_id UUID NOT NULL REFERENCES consumidores(id) ON DELETE CASCADE,
  produtor_id UUID NOT NULL REFERENCES produtores(id) ON DELETE CASCADE,
  total DOUBLE PRECISION NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'NOVO',
  produtor_notificado BOOLEAN NOT NULL DEFAULT FALSE,
  avaliacao_nota INTEGER CHECK (avaliacao_nota BETWEEN 1 AND 5),
  avaliacao_comentario VARCHAR(500),
  avaliacao_avaliado_em TIMESTAMP,
  cancelado_por VARCHAR(20),
  motivo_cancelamento TEXT,
  cancelado_em TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE order_items (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
  produto_id UUID NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
  nome VARCHAR(255) NOT NULL,
  quantidade INTEGER NOT NULL,
  preco_unitario DOUBLE PRECISION NOT NULL
);