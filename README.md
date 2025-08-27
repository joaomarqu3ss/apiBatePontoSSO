# 📂 API de Ponto Eletrônico

<img width="77" height="28" alt="image" src="https://github.com/user-attachments/assets/b07e00d1-fcec-41d1-8904-62f338fced57" />
<img width="96" height="28" alt="image" src="https://github.com/user-attachments/assets/b54edcb1-472f-41e8-bc75-f9fa2303f4dc" />



Esta é uma API REST desenvolvida com Spring Boot para o gerenciamento de pontos eletrônicos por usuário.
O acesso aos recursos é protegido por um mecanismo de autenticação via filters, garantindo que apenas usuários autenticados possam interagir com os endpoints.
O projeto utiliza Java 21, possui tratamento de exceções personalizadas, geração de logs estruturados e está preparado para fácil integração com front‑ends ou outros serviços.

---

# ✨ Funcionalidades
- ✅ **Autenticação de usuários**
- ✅ **Iniciar Servico, Pausar, Retornar, Sair**
- ✅ **Autorização por usuário (cada um bate o seu ponto)**
- ✅ **Filtros personalizados para autenticação**
- ✅ **Gestor obtém a localização de cada funcionário**
- ✅ **Tratamento de exceções customizado (com mensagens amigáveis no padrão JSON)**
- ✅ **Logs estruturados para monitoramento e auditoria**
- ✅ **Código pronto para extensões futuras (ex.: auditoria, multi‑tenant, etc.)**

---
## 🚀 Tecnologias utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot (Spring Web, Spring Boot DevTools, Spring Data JPA)**
- 🗄️ **Banco de dados relacional (PostgreSQL)**
- 🔑 **JWT para autenticação**
- 📦 **Maven para gerenciamento de dependências**