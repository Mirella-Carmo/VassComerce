# VassCommerce API

API REST de um pequeno e-commerce, desenvolvida para o Laboratório de Programação FullStack.

## Diagrama de Classe
![diagrama de classe](img.png)
## Rotas

Todas respondem e aceitam JSON.

| Método | Rota | Respostas |
|---|---|---|
| GET | `/categoria` | 200 |
| GET | `/categoria?nome=<nome>` | 200 |
| GET | `/categoria/{idcategoria}/produto` | 200, 404 |
| GET | `/produto/{id}` | 200, 404 |
| GET | `/produto?nome=&valorMinimo=&valorMaximo=` | 200 (filtros opcionais) |
| POST | `/produto` | 201 + header `Location`, 400 |
| GET | `/cliente/{id}` | 200, 404 |
| GET | `/cliente/{idcliente}/formas-de-pagamento` | 200, 404 |
| GET | `/cliente/{idcliente}/endereco` | 200, 404 |
| GET | `/cliente/{idcliente}/pedido` | 200, 404 |
| GET | `/tipo-cartao` | 200 |
| GET | `/estado` | 200 |
| GET | `/estado/{idestado}/cidade` | 200, 404 |

### Exemplo: cadastro de produto

```http
POST /produto
Content-Type: application/json

{
"nome": "Mouse sem fio",
"descricao": "Mouse 2.4GHz",
"fotoUrl": "https://exemplo.com/mouse.png",
"valorUnitario": 59.90,
"categoriaIds": [1]
}
```

Resposta `201 Created`, com `Location: /produto/6` e o produto criado no corpo.

### Exemplo: erro 400

```json
{
"error": "Invalid input",
"fields": [
    { "name": "nome", "message": "o nome é obrigatório" },
    { "name": "valorUnitario", "message": "o valor unitário deve ser maior que zero" }
],
"path": "/produto"
}
```

O mesmo formato é usado quando `categoriaIds` traz uma categoria inexistente.

### Como executar

Requisito: JDK 21.

```bash
./mvnw spring-boot:run        # Linux e macOS
mvnw.cmd spring-boot:run      # Windows
```
