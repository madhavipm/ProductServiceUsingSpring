# Product Service Spring API

## Project Overview
A robust and scalable Product Service API built with Spring Boot to manage products in an e-commerce system. This project emphasizes clean architecture, modular design, and efficient backend development practices.

---

## Key Features and Modules

### Configurations
- Centralized and reusable configuration management for streamlined application setup.

### Controller Advice
- Centralized exception handling for consistent and clean error responses.

### Controllers
- RESTful API endpoints for CRUD operations and product-related actions.

### Custom Exceptions
- Tailored exception classes for precise error management.

### DTOs (Data Transfer Objects)
- Ensured secure and efficient data transfer between layers.

### Inheritance Types
- Showcased object-oriented programming through various inheritance strategies.

### Models
- Represented core business entities with Hibernate annotations for database persistence.

### Repositories
- Utilized Spring Data JPA for seamless database interactions.

### Services
- Encapsulated business logic for scalable and reusable functionalities.

---

## Technologies and Tools Used
- **Frameworks**: Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Caching**: Redis
- **Messaging**: Kafka
- **Testing**: JUnit
- **Payment Integration**: Razorpay Payment Gateway
- **Build Tool**: Maven

---

## Setup Instructions

To run this project locally, follow these steps:
1. **Clone the repository**:
   ```bash
   git clone https://github.com/<madhavipm>/product-service-spring.git
2. **Navigate to the Project Directory**:
   ```bash
   cd product-service-spring-api
3. **Build the project: Use Maven to clean and build the project**:
   ```bash
   mvn clean install
4.**Run the application: Start the application using Maven**:
  ```bash
  mvn spring-boot:run




