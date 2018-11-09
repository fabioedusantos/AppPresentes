# AppPresentes

Monorepo didático que reúne o **aplicativo Android AppPresentes** e o **web service WSPresentes**, desenvolvidos em *live coding* durante as aulas da disciplina de **Desenvolvimento Mobile** do curso presencial de **Bacharelado em Sistemas de Informação**.

O repositório foi publicado originalmente como material de apoio aos alunos, permitindo revisar a integração entre uma aplicação Android nativa e um serviço REST com respostas em JSON. Nesta organização, os dois projetos históricos passam a viver no mesmo repositório para tornar mais clara a relação entre cliente, API e banco de dados.

> **Contexto histórico:** projeto acadêmico de 2018. O código foi mantido próximo ao que foi produzido em aula e, por isso, utiliza bibliotecas, APIs e práticas compatíveis com aquele período. Ele deve ser tratado como material didático e de consulta, não como referência de arquitetura ou segurança para uma aplicação atual em produção.

## Objetivos trabalhados em aula

- consumo de um web service REST em uma aplicação Android;
- comunicação HTTP com `HttpURLConnection`;
- serialização e desserialização JSON com Gson;
- execução de operações CRUD sobre uma API;
- uso de `Thread` e `runOnUiThread` para chamadas de rede;
- navegação entre `Activity` e envio de dados por `Intent`/`Bundle`;
- criação de formulários Android com `EditText`, `Spinner` e `DatePicker`;
- desenvolvimento de endpoints em PHP com CodeIgniter;
- persistência em MySQL/MariaDB;
- padronização de respostas JSON entre cliente e servidor;
- introdução de uma operação simples de autenticação no web service.

## Estrutura do monorepo

```text
AppPresentes/
├── android/                 # Aplicativo Android nativo em Java
│   ├── app/
│   ├── gradle/
│   ├── build.gradle
│   └── settings.gradle
├── backend/                 # Web service REST em PHP / CodeIgniter
│   ├── application/
│   ├── system/
│   ├── Banco.sql
│   └── index.php
├── .gitignore
└── README.md
```

## Aplicativo Android

O módulo `android/` contém o aplicativo cliente utilizado para cadastrar e consultar presentes.

### Tecnologias do projeto original

- Java;
- Android SDK 28;
- Android Support Library 28.0.0;
- ConstraintLayout 1.1.3;
- Gson 2.8.5;
- Gradle Plugin 3.2.1.

### Funcionalidades

- listagem dos presentes cadastrados;
- cadastro de um novo presente;
- edição de presente existente;
- remoção por toque prolongado no item da lista;
- indicação do destinatário (`noivo` ou `noiva`);
- seleção da data por `DatePicker`;
- atualização manual da lista;
- mensagens de retorno das operações executadas no web service.

A integração REST está concentrada em:

```text
android/app/src/main/java/fabio/prof/testews/model/PresentesModel.java
android/app/src/main/java/fabio/prof/testews/util/Http.java
android/app/src/main/java/fabio/prof/testews/util/HttpParam.java
```

O endereço do serviço está definido na constante `URL_BASE` de `PresentesModel`. O domínio utilizado nas aulas é histórico e pode não estar mais disponível; para executar o projeto hoje, altere essa URL para a instalação local ou remota do módulo `backend/`.

## Web service

O diretório `backend/` contém o serviço desenvolvido em PHP utilizando CodeIgniter 3.

O controller `Presentes` diferencia a operação pelo método HTTP recebido:

| Método | Recurso | Finalidade |
| --- | --- | --- |
| `GET` | `/Presentes` | listar todos os presentes |
| `GET` | `/Presentes?id={id}` | consultar um presente |
| `PUT` | `/Presentes?...` | cadastrar um presente |
| `POST` | `/Presentes` | atualizar um presente |
| `DELETE` | `/Presentes?id={id}` | remover um presente |
| `POST` | `/Login` | validar usuário e senha |

As respostas seguem o envelope utilizado durante as aulas:

```json
{
  "connected": true,
  "error": null,
  "response": {}
}
```

Dependendo da operação, `response` pode conter um objeto, uma coleção ou um valor booleano.

### Banco de dados

O arquivo abaixo contém a estrutura utilizada no projeto:

```text
backend/Banco.sql
```

Ele cria o banco `aula_ws_presentes` e as tabelas:

- `presentes` — dados dos presentes cadastrados;
- `usuarios` — estrutura utilizada pelo endpoint de login.

As credenciais reais usadas no ambiente de aula **não fazem parte deste histórico reconstruído**. A configuração do banco lê variáveis de ambiente:

```text
DB_HOST
DB_USER
DB_PASSWORD
DB_NAME
```

Há um exemplo em `backend/.env.example`. O CodeIgniter deste projeto não carrega esse arquivo automaticamente; ele serve apenas como referência. As variáveis devem ser disponibilizadas ao processo PHP pelo servidor web ou pelo ambiente de execução.

## Executando o backend

O projeto foi criado para um ambiente PHP/Apache típico da época. Para reproduzi-lo:

1. disponibilize o conteúdo de `backend/` em um servidor web com PHP compatível;
2. crie um banco MySQL ou MariaDB;
3. importe `backend/Banco.sql`;
4. defina `DB_HOST`, `DB_USER`, `DB_PASSWORD` e `DB_NAME` no ambiente do PHP;
5. confirme a configuração de `base_url`/reescrita de URL conforme seu servidor;
6. acesse o endpoint inicial para verificar a resposta JSON.

O `composer.json` do projeto declara PHP `>= 7.2.10`.

## Executando o Android

1. abra a pasta `android/` no Android Studio;
2. utilize um ambiente compatível com o Android Gradle Plugin 3.2.1;
3. ajuste a constante `URL_BASE` em `PresentesModel.java` para apontar para o seu backend;
4. execute o aplicativo em um emulador ou dispositivo.

Por se tratar de um projeto Android de 2018, versões atuais do Android Studio/Gradle/JDK podem exigir atualização das configurações do projeto antes da compilação.

O manifesto permite tráfego HTTP em texto claro porque o ambiente utilizado em aula consumia um endpoint HTTP. Isso é uma característica histórica do exemplo e não uma recomendação para aplicações atuais.

## Observações de segurança

Este material demonstra conceitos de integração e **não foi concebido como aplicação de produção**. Alguns pontos foram mantidos para preservar o contexto das aulas de 2018:

- autenticação simples com comparação direta de usuário e senha;
- estrutura de banco sem hash de senha;
- validações e tratamento de parâmetros simplificados;
- comunicação HTTP sem TLS no cliente original;
- ausência de token de sessão/autorização;
- consultas e manipulação de entrada que devem ser substituídas por técnicas atuais, como Query Builder/queries parametrizadas e validação adequada.

A única informação sensível removida durante a consolidação do monorepo foi a credencial de banco que estava gravada diretamente no projeto antigo.

## Histórico acadêmico

O desenvolvimento foi realizado progressivamente durante as aulas, em *live coding* com os alunos. Os repositórios originais foram publicados no GitHub para servirem como material de consulta fora da sala de aula.

Os registros históricos existentes indicavam os primeiros commits em **22 de outubro de 2018**, evolução do cadastro e da API em **1º de novembro de 2018** e inclusão de autenticação no web service em **8 de novembro de 2018**. O histórico deste monorepo foi reorganizado semanticamente para representar os principais marcos técnicos desse desenvolvimento, mantendo commits em dias úteis e horários noturnos compatíveis com o período das aulas.

## Autor e professor

**Fábio Eduardo dos Santos**  
Professor da disciplina de Desenvolvimento Mobile  
Bacharelado em Sistemas de Informação — modalidade presencial

---

Este repositório é mantido como registro acadêmico e material de consulta sobre uma implementação Android + REST produzida em sala de aula em 2018.
