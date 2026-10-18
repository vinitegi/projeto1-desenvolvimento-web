# Projeto Web (Spring Boot + Thymeleaf + H2)

Sistema web com menu lateral e 4 telas de cadastro. Roda na porta **8081**.

| Tela | Onde ficam os dados |
|------|---------------------|
| Funcionários | H2 do próprio projeto Web |
| Contas do Mês | H2 do próprio projeto Web |
| Veículos | Projeto **API** (via `RestClient`) |
| Serviços | Projeto **API** (via `RestClient`) |

## Requisitos
- Java 17 ou superior
- Maven (ou abrir a pasta no IntelliJ / Eclipse / STS e rodar a classe `WebApplication`)
- Para as telas de Veículos e Serviços, o **projeto API precisa estar rodando** na porta 8080

## Como executar
Primeiro suba a API (veja o README da pasta `api`), depois:
```
cd web
mvn spring-boot:run
```

- Sistema: http://localhost:8081
- Console do H2: http://localhost:8081/h2-console
  (JDBC URL: `jdbc:h2:mem:webdb`, usuário: `sa`, senha em branco)

Funcionários e Contas do Mês funcionam mesmo sem a API. Se a API estiver fora do ar, as telas de Veículos e Serviços mostram um aviso.

O endereço da API fica em `src/main/resources/application.properties` (`api.url`).

## Estrutura
```
src/main/java/com/projeto/web
├── WebApplication.java
├── model        (Funcionario, ContaDoMes)  -> salvos no H2 do Web
├── dto          (Veiculo, Servico)         -> dados que vêm da API
├── repository   (FuncionarioRepository, ContaDoMesRepository)
├── service      (ApiClient: chamadas HTTP para a API)
└── controller   (um controller por tela)

src/main/resources
├── templates    (fragments/layout.html = menu lateral reutilizável + 4 telas)
├── static/css   (style.css)
└── data.sql     (dados de exemplo)
```
