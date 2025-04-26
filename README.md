# 💵 Mini PicPay

<div align="center">
	<img src="docs/picpay-logo.svg" alt="Picpay logo" width="320" />
</div>

Mini PicPay is a Spring Boot application that simulates a financial transaction system, inspired by PicPay. The project enables users to perform transactions between wallets, with validations for balance, wallet type, external authorization, and notifications via Kafka. This is a study project to demonstrate the use of Spring Boot, Spring Data JDBC, Kafka, and integration with external APIs.

## ✨ Features

- **Wallet Management**:

  - Supports two wallet types: `COMMOM` (regular user) and `SHOPKEEPER` (merchant).
  - Each wallet includes details like full name, CPF, email, password, type, and balance.
  - Supports debit and credit operations on wallet balances.

- **Transactions**:

  - Allows creating transactions between wallets (payer and payee).
  - Validations:
    - The payer must have a `COMMOM` wallet.
    - The payer must have sufficient balance.
    - The payer cannot be the payee.
  - Records transactions with creation timestamps and stores them in an H2 database.

- **Authorization**:

  - Integrates with an external API (`https://util.devi.tools/api/v2/authorize`) to authorize transactions.
  - Throws an `UnauthorizedTransactionException` if authorization fails.

- **Notifications**:

  - Uses Apache Kafka to send and consume transaction notifications.
  - Integrates with an external API (`https://util.devi.tools/api/v1/notify`) to verify notification delivery.
  - Throws a `NotificationException` if notification fails.

- **REST APIs**:
  - `POST /transaction`: Creates a new transaction.
  - `GET /transaction`: Lists all transactions.

## 🛠️ Technologies Used

- **Java**: 17
- **Spring Boot**: 3.4.5
- **Spring Data JDBC**: For accessing the H2 database.
- **H2 Database**: In-memory database for development.
- **Apache Kafka**: For sending and consuming notifications.
- **RestClient**: For making calls to external APIs (authorization and notification).
- **Lombok**: To reduce boilerplate code.
- **Maven**: Dependency manager.

## 📋 Prerequisites

- **Java 17** or higher
- **Maven** 3.8.0 or higher
- **Docker** and **Docker Compose** installed

## 🔧 Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Mirian97/mini-picpay.git
   cd mini-picpay
   ```

2. **Set up Apache Kafka**:

   ```bash
   docker-compose up -d
   ```

3. **Build and run the application**:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the H2 console** (optional):
   - Access: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`, username: `developer`, password: empty.

## Usage

### Create a Transaction

- **Endpoint**: `POST /transaction`
- **Request Body** (JSON):
  ```json
  {
  	"payer": 1,
  	"payee": 2,
  	"value": 100.0
  }
  ```
- **Response**:
  - Success: `200 OK` with transaction details.
  - Error: `400 Bad Request` with an error message (e.g., insufficient balance).

### List Transactions

- **Endpoint**: `GET /transaction`
- **Response**: List of transactions in JSON.

### Initial SQL Scripts

The application uses the following SQL scripts to initialize the H2 database (in `src/main/resources`):

- **schema.sql**:

  ```sql
  CREATE TABLE IF NOT EXISTS WALLETS (
      id SERIAL PRIMARY KEY,
      fullName VARCHAR(100),
      cpf VARCHAR(50) NOT NULL,
      email VARCHAR(100) NOT NULL,
      password VARCHAR(100) NOT NULL,
      type INTEGER NOT NULL,
      balance DECIMAL(10, 2)
  );

  CREATE TABLE IF NOT EXISTS TRANSACTIONS (
      id SERIAL PRIMARY KEY,
      payer BIGINT,
      payee BIGINT,
      value DECIMAL(10, 2),
      createdAt TIMESTAMP
  );
  ```

- **data.sql**:

  ```sql
  DELETE FROM WALLETS;
  DELETE FROM TRANSACTIONS;

  INSERT INTO WALLETS (ID, FULLNAME, CPF, EMAIL, PASSWORD, TYPE, BALANCE)
  VALUES
      (1, 'Mirian Developer', '263.323.500-45', 'mirian@test.com', '123456', 2, 1000.00),
  ```

## 📜 License

This project is licensed under the [MIT License](docs/LICENSE.md).
