# Tarefator

Este é o frontend da aplicação Tarefator. ele foi feito pelo Angular. Este projeto ainda está `Em construção`!

## Requisitos para execução
- Node.js
- NPM
- Angular CLI

## Como Executar

Para executar o front desta aplicação, abra a ide de sua preferência (recomendação: VSCode), abra um terminal e digite o seguinte termo:

```bash
ng serve
```
Uma mensagem pode aparecer que a aplicação está rodando, mas se quiser você pode ir no navegador e digitar
 `http://localhost:4200/`. O Angular gera o site com qualquer mudança feita em seus arquivos, então cuidado!
```text
my-angular-app
├── src/
│   ├── app/
│   │   ├── components/      # Componentes reutilizáveis (Header, Footer, etc.)
│   │   ├── services/        # Serviços para comunicação com APIs
│   │   ├── app.component.ts # Lógica do componente raiz
│   │   ├── app.component.html
│   │   └── app.module.ts    # Módulo principal (imports e declarations)
│   ├── assets/              # Imagens, ícones e arquivos estáticos
│   ├── environments/        # Configurações de ambiente (dev/prod)
│   ├── index.html           # HTML base da aplicação
│   └── styles.scss          # Estilos globais (CSS/SASS)
├── angular.json             # Configuração do CLI e build
├── package.json             # Dependências e scripts (npm start, build)
├── tsconfig.json            # Configurações do TypeScript
└── README.md
```
