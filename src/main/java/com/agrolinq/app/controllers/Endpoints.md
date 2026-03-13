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