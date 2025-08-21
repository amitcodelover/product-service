# Product Service

This is a simple CRUD product service built with Spring Boot, Java, and Gradle.

## Prerequisites

- Docker

## Getting Started

This project is configured to run in Docker using a multi-stage build. This is the recommended way to run the application.

To build and run the application and its PostgreSQL database, use the following command:

```bash
docker-compose up --build
```

This single command will:
1.  Build the application's `.jar` file inside a temporary Docker container.
2.  Build the final, lightweight Docker image for the application.
3.  Start the application container and the PostgreSQL database container.

The API will be available at `http://localhost:8080`.

## API Usage

Here are sample `curl` commands for each endpoint.

---


### 1. Get All Products

Retrieves a list of all products.

**Request:**
`GET /products`

**Curl Command:**
```bash
curl -X GET http://localhost:8080/products
```

---


### 2. Get a Single Product by ID

Retrieves the product with the specified ID.

**Request:**
`GET /products/{id}`

**Curl Command (Example using ID 1):**
```bash
curl -X GET http://localhost:8080/products/1
```

---


### 3. Create a New Product

Creates a new product. The new product's data is sent as a JSON payload.

**Request:**
`POST /products`

**Curl Command:**
```bash
curl -X POST http://localhost:8080/products \
-H "Content-Type: application/json" \
-d '{"name":"MacBook Pro", "price":2499.99}'
```

---


### 4. Update an Existing Product

Updates the product with the specified ID.

**Request:**
`PUT /products/{id}`

**Curl Command (Example using ID 1):**
```bash
curl -X PUT http://localhost:8080/products/1 \
-H "Content-Type: application/json" \
-d '{"name":"MacBook Pro M4", "price":2799.99}'
```

---


### 5. Delete a Product

Deletes the product with the specified ID.

**Request:**
`DELETE /products/{id}`

**Curl Command (Example using ID 1):**
```bash
curl -X DELETE http://localhost:8080/products/1
```