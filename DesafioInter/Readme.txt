APIs REST 
 
Requisitos:
-JDK 
-Eclipse 

Tópicos:
-Java (v11)
-Projeto Maven
-Spring Boot(v2.5.5)
-Bd h2
-Autenticação JJWT
-Swagger (v2.9.2)
-Junit

Como compilar:
Pelo eclipse, sobre o arquivo "ApirestApplication.java" no caminho "DesafioInter/src/main/java/" package "com.desafiointer.DesafioInter" clicar em (run as) Java application.
Por padrão o servidor ficará disponível na porta 808.
Para acessar a documentação e executar as ações disponíveis basta acessar o link: "http://localhost:8080/swagger-ui.html"
 
Dados para token:
email: desafio@inter.com
senha: 1234
 
 
 
Por padrão todos os endpoints precisam de autenticação "STATELESS" via token.
 
Autenticação Resource "/auth"
 Podemos obter o token  através do endPoint "/auth" ou "http://localhost:8080/auth" 
 
 
Para obter o token, podemos enviar o email e senha pelo Swagger ou por uma requisição web, enviado um json pelo body
 
{
  "email": "desafio@inter.com",
  "senha": "1234"
}
 
A api irá retorna um json contendo: 
 
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgZGVzYWZpbyBpbnRlciIsInN1YiI6IjEiLCJpYXQiOjE2MzQxNzAxNDIsImV4cCI6MTYzNDI1NjU0Mn0.Qj3Xw9hXKt3pv5NYEQSfsqkcxGGNk5qBtd9Nloxfyt0",
    "tipo": "Bearer"
}
 
Para inserir o token via Swagger, temos que adicionar o parâmetro header "Authorization" o valor do tipo + espaço + token, ficando: "Bearer eyJhbGciOiJIUzI1..."
 
 
Empresa Resource "/empresa"
 
Podemos realizar o CRUD das empresas atraves de diversos endpoints.
Todos os endpoints desse método precisam ser autenticados pelo token.
 
Investimento Resource "/investimento"
Contendo apenas um endpoint, o usuário pode enviar via body um json contendo:
{
  "cpf": "string",
  "quantidadeEmpresas": 0,
  "valor": 0
}
 
Com isso, o sistema irá retornar a quantidade de ações que ele deve comprar por empresa listada na bolsa. 
O sistema obtém as ações de forma aleatória dentre as ações ativas no banco de dados.
-Caso o usuário informe uma quantidade de empresas superior a quantidade ativa no banco, o sistema levará em conta todas as ações ativas.
-O sistema irá informar o valor real investido e o troco. 



Teste Unitários:
Para executar os testes unitario,pelo eclipse, basta clicar com o botão direito sobre o diretorio "DesafioInter/src/test/java". 
Selecionar "Run as" -> "JUnit Test"
