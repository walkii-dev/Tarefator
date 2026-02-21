<div align="center">
<img src="./frontend/public/Tarefator.png" alt="Logo Oficial do Tarefator">
</div>

O Tarefator é uma aplicação na qual o usuário pode gerenciar as tarefas do dia-a-dia e captar as suas métricas de conclusão das tarefas.

## Tecnologias Utilizadas na Aplicação
- Java (Spring Boot) para o Backend;
- Angular para o Frontend;
- MySql para conexão a um banco de dados.

## Instalação (Requisitos)

- Uma IDE de sua preferência (VSCode, Intellij)
- JDK;
- Node;
- Angular CLI

## Etapas da Instalação

  1. clone o repositório em seu terminal ou na pasta que preferir (via windows 11 clicando na opção "abrir com terminal")

  ```bash
git clone https://github.com/walkii-dev/Tarefator.git
  ```

  2. após isso, abra o primeiro terminal, e navegue até a pasta do backend.

## Estrutura de Pastas da aplicação

```text
Tarefator/
    ├── frontend/           # Frontend da aplicação (Angular)
    │   ├── node-modules/   # Dependências
    │   ├── public/         # Arquivos da aplicação
    │   ├── src/            # Conteúdo principal (contém componentes)
    │   └── ...             #
    │
    ├── backend/            # Backend da aplicação (Java + Spring Boot)
    │    ├── src/           # Conteúdo principal (contém os endpoints)
    │    ├── target/        # Onde é gerado o executável da aplicação
    │    └── ...            # diversos arquivos específicos de configuração
    └── README.md
```

## Próximas melhorias
- implantar sistema de login e cadastro
- filtrar tarefas pelo tipo (concluídas)
- melhorias visuais no tipo de tarefa
- marcar tarefas como concluídas
- implementar docker
- possívelmente subir a aplicação para a nuvem
- colocar variáveis de ambiente e produção
- sincronizar horário do servidor com horário atual
- validação imediata de tarefa (na edição ou cadastro)


