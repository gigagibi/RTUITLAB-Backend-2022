# RTUITLAB-Backend-2022
This project is a pizzeria automation backend service.

This project allows managing orders, supplies and products information, also making an remote delivery orders through website.
## Tech stack
- Java
- Spring Boot (including Spring Security, Spring Data)
- Spring Cloud
- PostgreSQL
- MongoDB
- JWT (com.auth0 library)
- RabbitMQ
- Docker

**Additional**
- Lombok
- Swagger UI
- Maven
## Run with docker-compose
In root directory:
```bash
docker-compose up -d
```
## Project structure
Project is service-oriented, includes 6 services:
1. Eureka service (spring discovery service)
2. Gateway service
3. Products service (used for managing information about products from menu)
4. Orders service (used for managing orders, including delivery orders)
5. Supplies service (used for managing supplies)
6. Deliveries service (backend of website that can be used by clients for making remote delivery orders)
