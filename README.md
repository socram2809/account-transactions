# Account Transactions

A Spring Boot application for managing account transactions with RESTful APIs.

## Features

- Account creation and listing
- Transaction registration
- OpenAPI (Swagger) documentation

## Getting Started

### Clone the repository

```bash

git clone https://github.com/socram2809/account-transactions.git
cd account-transactions
```

### Run with Docker

#### Requirements
- Docker
- Docker Compose

#### Make run.sh file executable

```bash
chmod +x run.sh
```

#### Build and run the application
```bash
./run.sh
```

#### Stop the application

```bash
docker-compose down
```

### Run with Gradle

#### Requirements

- Java 21

#### Build the project

```bash
./gradlew build
```

#### Run the application

```bash
./gradlew bootRun
```

#### Stop the application
Press `Ctrl + C` in the terminal where the application is running.

### Access the API
The application will run on `http://localhost:8080`. You can access the API documentation at `http://localhost:8080/swagger-ui/index.html`.