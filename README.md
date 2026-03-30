# ObraCerta Backend API

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.10-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue.svg)
![Docker](https://img.shields.io/badge/Docker-ready-blue.svg)

API RESTful desenvolvida em Java com Spring Boot para o ecossistema ObraCerta. Este serviço gerencia autenticação, controle de usuários, além de possuir regras de negócios voltadas para cálculos e gerenciamento de tarefas/projetos.

## 📑 Sumário
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Como Instalar e Executar](#-como-instalar-e-executar)
- [Uso da API (Exemplos)](#-uso-da-api-exemplos)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

## ✨ Funcionalidades
* **Autenticação e Segurança:** Login baseado em sessão criptografada utilizando Spring Security e BCrypt.
* **Gerenciamento de Usuários (CRUD):** Criação, listagem, atualização e exclusão de contas de usuários com validação de dados.
* **CORS e CSP:** Configurado para acesso seguro entre domínios integrando com plataformas frontend (Vercel).
* **Dockerizado:** Imagem Otimizada com multi-stage build via Alpine Linux.

## 🛠 Tecnologias Utilizadas
* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot 3.5.10
* **Segurança:** Spring Security
* **Persistência de Dados:** Spring Data JPA
* **Bancos de Dados:** PostgreSQL (Produção) / H2 Database (Testes e Desenvolvimento)
* **Gerenciador de Dependências:** Maven
* **Variáveis de Ambiente:** Spring Dotenv (`me.paulschwarz`)
* **Infraestrutura:** Docker

## ⚙️ Pré-requisitos
Certifique-se de possuir em seu ambiente:
- Java JDK 21+
- Apache Maven 3.8+
- Docker e Docker Compose (Opcional, para conteinerização)

## 🚀 Como Instalar e Executar

**1. Clone o repositório:**
```bash
git clone [https://github.com/seu-usuario/obracerta-backend.git](https://github.com/seu-usuario/obracerta-backend.git)
cd obracerta-backend
