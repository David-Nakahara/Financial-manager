# 💰 MoneyMind — Seu Gestor Financeiro
## 📌 Sobre o Projeto

O MoneyMind é uma aplicação Full Stack de gestão financeira, desenvolvida para ajudar usuários a organizar contas, registrar transações e visualizar movimentações financeiras de forma simples e intuitiva.

A plataforma permite acompanhar receitas e despesas, mantendo um histórico organizado e oferecendo visualização de dados através de gráficos.


# 🌐 Demonstração Online

A aplicação está disponível online:

🔗 https://financialgestor.netlify.app

## ⚠️  O backend está hospedado na plataforma Render em plano gratuito, Após períodos de inatividade, o servidor entra em modo de hibernação e pode levar alguns segundos para responder à primeira requisição enquanto é reativado. ⚠️



# 🚀 Tecnologias Utilizadas
## Front-end

React

Vite

JavaScript

Recharts

ESLint

## Backend

Java 21

Spring Boot

Spring Security

Spring Data JPA

JWT

Lombok

## Banco de Dados

⚠️ H2 Database (utilizado no desenvolvimento)

O H2 foi escolhido para acelerar o desenvolvimento local.
Para ambientes de produção, o projeto está preparado para migração para PostgreSQL.

# 🧠 Arquitetura

O sistema segue arquitetura Full Stack com API REST.

## Fluxo da aplicação:

1️⃣ Usuário acessa o frontend
2️⃣ Frontend consome a API REST
3️⃣ Backend processa regras de negócio
4️⃣ Dados são persistidos no banco
5️⃣ Informações retornam para exibição na interface

# 📦 Funcionalidades

✔ Autenticação com JWT
✔ Gestão de contas financeiras
✔ Registro de transações
✔ Dashboard com gráficos financeiros
✔ Histórico de movimentações

# 🖼 Demonstração

![Dashboard](back/gifsgithub-ignore/dashboard.gif)
![Criando Transação](back/gifsgithub-ignore/transacao.gif)
![Sistema](back/gifsgithub-ignore/meusistema.gif)
🚀 Como Executar
Backend
cd bank-backend/back
./gradlew bootRun

Servidor:
http://localhost:8080

Frontend
cd bank-frontend
npm install
npm run dev

Servidor:
http://localhost:5173

# 👨‍💻 Desenvolvido por DevDavid

Full Stack Developer focado em desenvolvimento de aplicações web modernas.

📧 devdavidnakahara@gmail.com

📸 @woo_d4vid
