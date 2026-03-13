# AgroLinq API Endpoints

## Auth `/api/auth`

| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/api/auth/login` | Login for all user types | ❌ |
| POST | `/api/auth/register/consumidor` | Register consumidor | ❌ |
| POST | `/api/auth/register/produtor` | Register produtor | ❌ |
| POST | `/api/auth/register/restaurante` | Register restaurante | ❌ |

### POST `/api/auth/login`
**Request:**
```json
{
  "email": "user@email.com",
  "senha": "123456"
}
```
**Response `200`:**
```json
{
  "token": "eyJhbGci...",
  "id": "uuid",
  "nome": "Nome do usuário",
  "email": "user@email.com",
  "tipo": "consumidor | produtor | restaurante | admin"
}
```

---

### POST `/api/auth/register/consumidor`
**Request:**
```json
{
  "nome": "Nome",
  "email": "user@email.com",
  "cpf": "000.000.000-00",
  "senha": "123456"
}
```
**Response `201`:**
```json
{
  "token": "eyJhbGci...",
  "id": "uuid",
  "nome": "Nome",
  "email": "user@email.com",
  "tipo": "consumidor"
}
```

---

### POST `/api/auth/register/produtor`
**Request:**
```json
{
  "nome": "Nome",
  "email": "user@email.com",
  "cpf": "000.000.000-00",
  "senha": "123456",
  "nomeFazenda": "Fazenda São João",
  "descricaoFazenda": "Descrição",
  "localizacao": "Holambra, SP",
  "telefone": "19999999999",
  "whatsapp": "19999999999"
}
```
**Response `201`:**
```json
{
  "token": "eyJhbGci...",
  "id": "uuid",
  "nome": "Nome",
  "email": "user@email.com",
  "tipo": "produtor"
}
```

---

### POST `/api/auth/register/restaurante`
**Request:**
```json
{
  "nome": "Nome",
  "email": "user@email.com",
  "cnpj": "XX.XXX.XXX/XXXX-XX",
  "senha": "123456",
  "nomeEstabelecimento": "Restaurante XYZ",
  "descricaoEstabelecimento": "Descrição",
  "localizacao": "São Paulo, SP",
  "telefone": "11999999999",
  "whatsapp": "11999999999"
}
```
**Response `201`:**
```json
{
  "token": "eyJhbGci...",
  "id": "uuid",
  "nome": "Nome",
  "email": "user@email.com",
  "tipo": "restaurante"
}
```
---

## Produtos `/api/produtos`

| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/api/produtos` | Create product | ✅ Produtor |
| PUT | `/api/produtos/{id}` | Update product | ✅ Produtor |
| GET | `/api/produtos/{id}` | Get product by id | ❌ |
| GET | `/api/produtos/produtor/{produtorId}` | List by produtor | ❌ |
| GET | `/api/produtos/categoria/{categoria}` | List by category | ❌ |
| GET | `/api/produtos` | List all | ❌ |
| DELETE | `/api/produtos/{id}` | Delete product | ✅ Produtor |

### POST `/api/produtos`
**Headers:** `x-user-id: uuid`

**Request:**
```json
{
  "nome": "Tomate Cereja",
  "descricao": "Tomate cereja fresco",
  "preco": 12.50,
  "imagemUrl": "https://...",
  "categoria": "Hortifruti",
  "estoque": 100,
  "unidade": "kg",
  "descricaoDetalhada": "Descrição detalhada",
  "origem": "Fazenda São João, Holambra-SP",
  "certificacoes": ["Orgânico"],
  "imagensAdicionais": ["https://..."],
  "especificacoes": { "peso": "500g" }
}
```
**Response `201`:**
```json
{
  "id": "uuid",
  "nome": "Tomate Cereja",
  "descricao": "Tomate cereja fresco",
  "preco": 12.50,
  "imagemUrl": "https://...",
  "categoria": "Hortifruti",
  "produtorId": "uuid",
  "produtorNome": "João Silva",
  "estoque": 100,
  "unidade": "kg",
  "descricaoDetalhada": "Descrição detalhada",
  "origem": "Fazenda São João, Holambra-SP",
  "certificacoes": ["Orgânico"],
  "imagensAdicionais": ["https://..."],
  "especificacoes": { "peso": "500g" },
  "createdAt": "2026-01-01T00:00:00",
  "updatedAt": "2026-01-01T00:00:00"
}
```

---

### PUT `/api/produtos/{id}`
**Headers:** `x-user-id: uuid`

**Request:** same as POST

**Response `200`:** same as POST response

---

### GET `/api/produtos/{id}`
**Response `200`:** same as POST response

---

### GET `/api/produtos/produtor/{produtorId}`
**Response `200`:** array of produto responses

---

### GET `/api/produtos/categoria/{categoria}`
**Response `200`:** array of produto responses

---

### GET `/api/produtos`
**Response `200`:** array of produto responses

---

### DELETE `/api/produtos/{id}`
**Headers:** `x-user-id: uuid`

**Response `204`:** no content

---