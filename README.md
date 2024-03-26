
# ConcertCapstone Project

## OVERVIEW
The ConcertCapstone project is a robust Spring Boot application for managing concert bookings, featuring user authentication, concert listings, booking management, admin functionalities, RESTful web services, and leverages Jakarta Persistence API (JPA) with Hibernate for database operations. The front-end is enriched with JavaScript to enhance user interactions and provide a dynamic and responsive user experience## OVERVIEW
The ConcertCapstone project is a robust Spring Boot application for managing concert bookings, featuring user authentication, concert listings, booking management, admin functionalities, RESTful web services, and leverages Jakarta Persistence API (JPA) with Hibernate for database operations. The front-end is enriched with JavaScript to enhance user interactions and provide a dynamic and responsive user experience## OVERVIEW
The ConcertCapstone project is a robust Spring Boot application for managing concert bookings, featuring user authentication, concert listings, booking management, admin functionalities, RESTful web services, and leverages Jakarta Persistence API (JPA) with Hibernate for database operations. The front-end is enriched with JavaScript to enhance user interactions and provide a dynamic and responsive user experience
## Feature
User Authentication: Secure user registration and login functionalities.

Concert Management: Functionalities to browse concerts, book tickets, and manage bookings.

Admin Panel: Backend interface for user and concert management.

Responsive UI: A front-end designed with Thymeleaf, Bootstrap, and enriched with JavaScript.

RESTful Web Services: Programmatic access to application functionalities.

Hibernate with JPA: Efficient ORM for database interactions.
## Technology Stack

Spring Boot

Spring Security

MySQL

Thymeleaf

Bootstrap & Custom CSS

JavaScript for dynamic content

JUnit for testing

Spring REST for web services

Hibernate ORM with Jakarta Persistence API
## Getting Started

### Setting Up and Running the Application

    Clone the repository.
    Configure application.properties for your database.
    Use mvn spring-boot:run to launch the application.
    Access the app at http://localhost:8080.
### Jakarta Persistence API (JPA) with Hibernate

The application leverages JPA with Hibernate for object-relational mapping, providing a powerful layer of abstraction for database interactions. Hibernate ORM enables efficient transaction management and CRUD operations on the entities.
Configuration

Ensure your application.properties file includes the correct database settings and Hibernate properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/yourDatabase?useSSL=false
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```
### Using the RESTful Web Services

The application provides RESTful web services to interact with concert and booking data. These services follow standard HTTP methods (GET, POST, PUT, DELETE) for CRUD operations.
Endpoints

/api/concerts - Retrieve all concerts or add a new concert.

/api/concerts/{id} - Get, update, or delete a concert by ID.
    
/api/bookings - Get all bookings or create a new booking.

/api/bookings/{id} - Get, update, or cancel a booking by ID.

Example: Fetching Concerts with curl

```
curl -X GET http://localhost:8080/api/concerts
```