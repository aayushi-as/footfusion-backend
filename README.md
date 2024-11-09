# Footfusion E-commerce Backend

Footfusion is a backend application for a footwear e-commerce platform. It provides a range of features for user authentication, product browsing, and order processing, as well as advanced management capabilities for admin users. This backend is designed with Spring Boot, leveraging Spring Security, JWT for authentication, and Spring Data JPA for database interactions.

## Features

### Authentication
- JWT token-based authentication for secure API access

### User Management Features
- **Registration and Login**: User sign-up and authentication
- **Product Filtering**: Filter products based on various criteria
- **Wishlist Service**: Add products to a wishlist for easy access
- **Cart Service**: Manage cart items for order placement
- **Reviews**: Write reviews for products
- **Order Products**: Place orders for products in the cart
- **Order History**: View past orders

### Admin Role
- **Inventory Management**: Add and manage product inventory
- **Price Updates**: Modify product prices
- **Category and Brand Management**: Add and update product categories and brands
- **SKU Management**: Update product SKUs for inventory tracking

## API Documentation
The API endpoints are documented with Swagger UI, providing an interactive interface to test and view available endpoints. 
- **Swagger UI**: [https://aayushi-as.github.io/footfusion-backend/](#)

## Technology Stack

- **Spring Boot**: Core framework for building the backend
- **Spring Boot Security**: For securing endpoints and managing user roles
- **Spring Data JPA**: Database persistence and management with MySQL
- **MySQL**: Database for storing application data
- **JWT**: JSON Web Token for secure user authentication
- **Maven**: Dependency management
- **Swagger UI**: API documentation

## Getting Started

### Prerequisites
- Java 11 or higher
- MySQL database
- Maven for dependency management

### Installation
Clone the project

```bash
  git clone https://github.com/aayushi-as/footfusion-backend.git
```

Configure MySQL database in application.properties
```bash
  spring.datasource.username = your-username
  spring.datasource.password = your-password
```

Build and run the application

```bash
  mvn clean install
  mvn spring-boot:run
```


## License
This project is licensed under the [MIT](https://github.com/aayushi-as/footfusion-backend?tab=MIT-1-ov-file) License.
