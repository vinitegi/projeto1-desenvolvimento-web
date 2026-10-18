# Sistema de Gestão

Este é um projeto universitário com dois sistemas que conversam entre si: uma **API** e um **site (Web)**. A ideia é simples: ter um lugar para cadastrar funcionários, contas do mês, veículos e serviços, tudo navegando por um menu lateral.

Foi feito em Java com Spring Boot e usa o banco H2, que roda dentro do próprio programa. Você não precisa instalar nem configurar nenhum banco de dados.

---

## Como o projeto está dividido

O professor passou um diagrama de classes com dois grupos, e foi assim que organizamos:

| Projeto | O que tem | Onde guarda os dados | Porta |
|---------|-----------|----------------------|-------|
| **web** (Spring Boot + Thymeleaf) | Telas de **Funcionários** e **Contas do Mês** | No H2 do próprio Web | 8081 |
| **api** (Spring Boot REST) | **Veículos** e **Serviços** | No H2 da API | 8080 |

O site mostra as quatro telas no mesmo menu. As duas primeiras funcionam sozinhas. Já **Veículos** e **Serviços** pedem os dados para a API, então ela precisa estar rodando para essas telas funcionarem.

```
Navegador  ──►  Web (8081)  ──►  API (8080)
                  │                 │
                H2 do Web        H2 da API
        (Funcionários e Contas)  (Veículos e Serviços)
```

---

## O que você precisa ter instalado

- **Java 17 ou mais novo** (para conferir: `java -version`)
- **Maven** (para conferir: `mvn -version`)

Se preferir não usar o terminal, dá para abrir as pastas `api` e `web` no IntelliJ, Eclipse ou STS e rodar as classes `ApiApplication` e `WebApplication`.

---

## Como rodar

Você vai precisar de **dois terminais**, um para cada projeto. A ordem importa: **API primeiro**.

**Terminal 1: API**
```
cd api
mvn spring-boot:run
```
Quando aparecer algo como `Started ApiApplication`, ela está no ar em http://localhost:8080.

**Terminal 2: Web**
```
cd web
mvn spring-boot:run
```
Agora é só abrir **http://localhost:8081** no navegador.

Para parar qualquer um dos dois, use `Ctrl + C` no terminal.

---

## O que dá para fazer em cada tela

Todas seguem o mesmo jeito, parecido com o protótipo aprovado: um formulário em cima ("Novo cadastro") e a lista de registros embaixo.

- **Salvar** cadastra um registro novo (ou grava a edição, se você estiver editando).
- **Limpar** esvazia o formulário e cancela uma edição em andamento.
- **Editar** (na linha da tabela) joga o registro de volta no formulário.
- **Excluir** apaga o registro, depois de pedir confirmação.

Alguns detalhes:
- O CPF do funcionário e a placa do veículo identificam o registro, então não dá para cadastrar dois iguais, e na edição esse campo fica travado.
- O ID de Contas do Mês e de Serviços é gerado sozinho.
- Se faltar algum campo ou o valor for inválido (salário negativo, ano fora do normal, etc.), a tela avisa embaixo do campo.

Já vem com alguns dados de exemplo para você ver as tabelas preenchidas logo de cara.

---

## Uma coisa importante sobre os dados

O H2 está configurado **em memória**. Isso quer dizer que tudo o que você cadastrar **some quando desligar o programa**, e na próxima vez ele volta só com os dados de exemplo. Para um trabalho de faculdade isso ajuda: sempre começa limpo.

Se quiser ver as tabelas por dentro, existe o console do H2:

- API: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:apidb`)
- Web: http://localhost:8081/h2-console (JDBC URL: `jdbc:h2:mem:webdb`)
- Usuário: `sa`, senha em branco

---

## Testando a API sem o site

A API tem endpoints REST, então dá para testar direto no navegador ou no Postman.

| Método | Rota | O que faz |
|--------|------|-----------|
| GET | `/api/veiculos` | Lista os veículos |
| GET | `/api/veiculos/{placa}` | Busca um veículo pela placa |
| POST | `/api/veiculos` | Cadastra um veículo |
| PUT | `/api/veiculos/{placa}` | Atualiza um veículo |
| DELETE | `/api/veiculos/{placa}` | Exclui um veículo |
| GET | `/api/servicos` | Lista os serviços |
| GET | `/api/servicos/{id}` | Busca um serviço pelo id |
| POST | `/api/servicos` | Cadastra um serviço |
| PUT | `/api/servicos/{id}` | Atualiza um serviço |
| DELETE | `/api/servicos/{id}` | Exclui um serviço |

O exemplo mais fácil é abrir http://localhost:8080/api/veiculos no navegador. Ele devolve a lista em JSON.

Para mais detalhes (exemplos de JSON, validações), veja o `README.md` dentro da pasta `api`. O da pasta `web` explica a estrutura do site.

---

## Se algo der errado

**"Port 8080 was already in use" (ou 8081)**
Outro programa está usando a porta. Feche-o, ou troque o número em `src/main/resources/application.properties` (`server.port`). Se trocar a porta da API, lembre de atualizar o `api.url` no `application.properties` do Web.

**As telas de Veículos e Serviços mostram um aviso vermelho**
A API não está rodando (ou está em outra porta). Suba o projeto `api` primeiro e recarregue a página.

**`mvn` não é reconhecido**
O Maven não está instalado ou não está no PATH. Instale-o, ou rode os projetos pela IDE.

**Erro falando de versão do Java (`release version 17 not supported`)**
Seu Java é mais antigo que o 17. Instale um JDK 17 ou superior.

**Cadastrei coisas e elas sumiram**
É o H2 em memória, como explicado acima. Ao reiniciar, os dados voltam ao exemplo.

---

## Mapa das pastas

```
projeto1-desenvolvimento-web
├── api/                 Projeto da API
│   └── src/main/java/com/projeto/api
│       ├── model        Veiculo e Servico
│       ├── repository   acesso ao banco
│       └── controller   os endpoints REST
│
└── web/                 Projeto do site
    └── src/main
        ├── java/com/projeto/web
        │   ├── model        Funcionario e ContaDoMes (salvos no H2 do Web)
        │   ├── dto          Veiculo e Servico (vêm da API)
        │   ├── repository   acesso ao banco
        │   ├── service      ApiClient, quem conversa com a API
        │   └── controller   uma tela por controller
        └── resources
            ├── templates    as páginas HTML (Thymeleaf) e o menu lateral
            ├── static/css   o visual
            └── data.sql     dados de exemplo
```

---

## Tecnologias

Java 17 · Spring Boot · Spring Data JPA · Thymeleaf · H2 · Maven
