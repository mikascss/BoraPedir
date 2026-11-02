# 🍔 Comandaê — Sistema de Restaurante

Sistema de restaurante desenvolvido em **Java**, com interação pelo **console**, para praticar programação orientada a objetos, estruturas de dados, padrões de projeto e testes automatizados.

O projeto contempla o gerenciamento de **cardápio e pedidos**, a **personalização de lanches** e o **fechamento das vendas do dia**.

> **Sobre este README:** a descrição abaixo foi organizada a partir do enunciado do projeto. Marque as funcionalidades concluídas e ajuste os nomes das classes conforme o código efetivamente implementado.

## 📋 Funcionalidades do projeto

- [X] **Cardápio:** disponibilizar X-Burger (R$ 16,00), X-Salada (R$ 18,00) e X-Bacon (R$ 22,00).
- [X] **Pedidos:** cadastrar pedidos com número gerado automaticamente e nome do cliente.
- [X] **Itens do pedido:** adicionar lanches e calcular o total de cada comanda.
- [X] **Listagem:** consultar os pedidos registrados durante o dia.
- [X] **Fechamento diário:** somar os valores dos pedidos e mostrar o total vendido.
- [X] **Adicionais:** acrescentar cheddar (+ R$ 4,00) e bacon extra (+ R$ 6,00), inclusive em combinação.
- [X] **Personalização:** retirar queijo, alface, tomate ou cebola sem alterar o preço-base.
- [X] **Edição de pedidos:** buscar por número, acrescentar/remover itens e cancelar pedidos.
- [X] **Tratamento de entradas inválidas:** impedir que opções incorretas ou dados não numéricos encerrem a aplicação inesperadamente.

### Funcionalidades opcionais

- [X] Registro de gorjetas e relatório financeiro.
- [ ] Controle de transações sem duplicidade.
- [ ] Relatório de lanches-base distintos.
- [ ] Geração de arquivo executável.

## 🛠️ Tecnologias e conceitos

- **Java:** desenvolvimento da aplicação e aplicação dos conceitos de orientação a objetos.
- **Maven:** gerenciamento de dependências do projeto.
- **JUnit 5:** testes automatizados de validações e regras de cálculo.
- **Collections Framework:** uso de `ArrayList`, `HashMap` e `HashSet`.
- **Tratamento de exceções:** validações com `IllegalArgumentException` e tratamento de erros de entrada, como `InputMismatchException`.

## 🧩 Padrões de projeto e boas práticas

| Prática ou padrão | Aplicação prevista |
| --- | --- |
| **SRP (responsabilidade única)** | `Pedido` representa uma comanda; `GerenciadorPedidos` mantém o conjunto de pedidos; `MenuConsole` cuida da interação. |
| **Facade** | `RestauranteFacade` oferece um ponto central de acesso às operações do restaurante. |
| **Factory** | `LancheFactory` centraliza a criação de novos lanches do cardápio. |
| **Decorator** | Os adicionais envolvem um `ItemCardapio` e acrescentam preço e descrição sem modificar a classe-base. |
| **Encapsulamento** | Atributos privados e validações nos setters e construtores. |
| **Polimorfismo** | Uso da interface `ItemCardapio` para tratar lanches simples e lanches com adicionais. |
| **Separação de responsabilidades** | `Main` controla o fluxo, o menu lê e exibe dados e as classes de domínio executam as regras de negócio. |
| **Testes unitários** | Verificação de regras como rejeitar preços negativos e calcular o preço de adicionais. |

## 📁 Estrutura 

```text
src/
├── main/java/
│   ├── app/
│   │   ├── Main.java
│   │   ├── MenuConsole.java
│   │   └── RestauranteFacade.java
│   ├── cardapio/
│   │   ├── ItemCardapio.java
│   │   ├── Lanche.java
│   │   ├── LancheFactory.java
│   │   ├── ComCheddar.java
│   │   └── ComBaconExtra.java
│   ├── pedido/
│   │   ├── Pedido.java
│   │   └── GerenciadorPedidos.java
│   ├── financeiro/             # opcional
│   └── util/                   # opcional
└── test/java/                  # testes JUnit
```

> A árvore acima é uma **referência do enunciado**, não uma inspeção da estrutura atual do repositório.

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/mikascss/BoraPedir.git
   ```
2. Abra o projeto no **IntelliJ IDEA**.
3. Importe as dependências do **Maven** pelo `pom.xml`.
4. Execute a classe `Main`.

Para executar os testes, no terminal do projeto:

```bash
mvn test
```

## 🧪 Cenários de teste propostos

- Validar que um lanche não aceita nome vazio ou preço negativo.
- Verificar que um X-Bacon (R$ 22,00) com cheddar (+ R$ 4,00) custa **R$ 26,00**.
- Conferir o cálculo do total de um pedido e o fechamento do dia.
- Garantir que um pedido cancelado não seja incluído no fechamento.
- Conferir a contagem de combinações diferentes de lanches.

## 🎥 Demonstração

**Vídeo do projeto:** [link da apresentação](https://drive.google.com/file/d/1xNFeC4LAWhySswbXUCwvJ1VRjsTN6BSw/view?usp=sharing)

## 📚 Objetivo de aprendizagem

Consolidar conhecimentos de **POO**, **SOLID**, padrões **Factory, Decorator e Facade**, **coleções Java**, validação de dados e **testes unitários**, construindo o sistema de maneira incremental.

---

**Projeto educacional desenvolvido para praticar fundamentos de desenvolvimento em Java.**
