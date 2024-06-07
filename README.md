## Backeend da solução Bright Ocean (Java/Spring Boot)


#### A aplicação deve ser uma API RESTful, construída com os frameworks Spring/Spring Boot. <br/>

A entrega deve atender aos seguintes requisitos: <br/><br/>

[x] Utilização de anotações do Spring para configuração de beans e injeção de dependências <br/>
[x] Camada model / DTO com utilização correta dos métodos de acesso <br/>
[x] Persistência de dados com Spring Data JPA <br/>
[x] Mapeamento de relacionamentos entre tabelas e pesquisas <br/>
[x] Validação com Bean Validation <br/>
[x] Paginação para recursos com muitos registros <br/>
[x] HATEOAS para atender ao Nível 3 de Maturidade proposto por Leonard Richardson <br/>
[x] Tratamento adequado dos erros e exceptions <br/>
[x] Documentação com SWAGGER <br/>
[x] Utilização adequada dos verbos HTTP e códigos de status <br/>
[X] Deploy em nuvem <br/>
[ ] Integração com modelo de Inteligência Artificial <br/>


## Projeto Bright Ocean: Solução para Monitoramento da Saúde dos Recifes de Corais

### Introdução

Os recifes de corais são fundamentais para a biodiversidade marinha e a saúde dos oceanos. Contudo, esses ecossistemas estão sob ameaça devido às mudanças climáticas, poluição e outras atividades humanas. Para preservar e proteger os corais, é essencial monitorar sua saúde de maneira eficiente e abrangente. O Projeto Bright Ocean visa utilizar tecnologia de visão computacional para analisar imagens subaquáticas dos corais e avaliar seu estado de saúde em tempo real.

### O Problema

Os recifes de corais enfrentam diversas ameaças, incluindo:

- *Mudanças Climáticas*: Aumento da temperatura da água e acidificação dos oceanos.
- *Poluição*: Despejo de resíduos quím…

## Introdução

Esta documentação fornece informações detalhadas sobre como utilizar a API BrightOcean. A API está protegida por autenticação JWT e utiliza o Swagger para documentação interativa.

## Sumário

1. [Cadastro de Usuário](#cadastro-de-usuario)
2. [Obtenção do Token JWT](#obtencao-do-token-jwt)
3. [Utilização do Token JWT no Swagger](#utilizacao-do-token-jwt-no-swagger)
4. [Acesso aos Endpoints Protegidos](#acesso-aos-endpoints-protegidos)

## Cadastro de Usuário

### Endpoint: /usuario/cadastro

Para utilizar a API, o primeiro passo é criar um usuário.

*Método:* POST

*URL:* https://brightocean-prod.azuremicroservices.io/api/usuario/cadastro

*Body da Requisição:*

```
json
{
"nome": "Seu Nome",
"cpf": "123.456.789-10",
"email": "seuemail@example.com",
"senha": "suaSenha123",
"perfil": "USUARIO"
}
```

*Exemplo de Curl:*

```
sh
curl -X POST "https://brightocean-prod.azuremicroservices.io/api/usuario/cadastro" -H "Content-Type: application/json" -d '{
"nome": "Seu Nome",
"cpf": "123.456.789-10",
"email": "seuemail@example.com",
"senha": "suaSenha123",
"perfil": "USUARIO"
}'
```

*Resposta de Sucesso:*

```
json
{
"id": 1,
"nome": "Seu Nome",
"cpf": "123.456.789-10",
"email": "seuemail@example.com",
"perfil": "USUARIO"
}
```

## Obtenção do Token JWT

### Endpoint: /login/token

Após criar um usuário, você precisará obter um token JWT para acessar os endpoints protegidos.

*Método:* POST

```
*URL:* https://brightocean-prod.azuremicroservices.io/api/login/token
```

*Body da Requisição:*

```
json
{
"email": "seuemail@example.com",
"senha": "suaSenha123"
}
```

*Exemplo de Curl:*

```
sh
curl -X POST "https://brightocean-prod.azuremicroservices.io/api/login/token" -H "Content-Type: application/json" -d '{
"email": "seuemail@example.com",
"senha": "suaSenha123"
}'
```

*Resposta de Sucesso:*

```
json
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Utilização do Token JWT no Swagger

1. Acesse a interface do Swagger [https://brightocean-prod.azuremicroservices.io/api/swagger-ui/index.html](https://brightocean-prod.azuremicroservices.io/api/swagger-ui/index.html).
2. No canto superior direito, clique no botão "Authorize".
3. Insira o token JWT no formato Bearer <seu_token_jwt>.
4. Clique em "Authorize" e depois em "Close".

## Acesso aos Endpoints Protegidos

Após autorizar o token JWT, você poderá acessar os demais endpoints da API que estavam protegidos.

### Exemplo de Endpoint Protegido: /dados/privado

*Método:* GET

*URL:* /dados/privado

*Header de Autorização:*

http
Authorization: Bearer <seu_token_jwt>

*Exemplo de Curl:*

sh
curl -X GET "https://brightocean-prod.azuremicroservices.io/api/usuarios" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."

*Resposta de Sucesso:*

```
json
{
   "Lista de usuarios"
}
```

---

## Considerações Finais

Certifique-se de proteger adequadamente o token JWT e não compartilhá-lo com terceiros. Caso tenha dúvidas ou problemas, consulte a documentação completa ou entre em contato com o suporte técnico.

---

Espero que isso ajude a destacar o seu projeto BrightOcean!


