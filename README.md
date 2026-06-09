# Task Manager API

A RESTful Task Manager API built with Java and Spring Boot.

This project was created to strengthen my backend development skills and improve my understanding of REST APIs, layered architecture, database integration, authentication, authorization, and clean code practices.

---

## Features

- User registration
- User login with JWT authentication
- BCrypt password hashing
- Create tasks
- Get all tasks for the authenticated user
- Get task by ID
- Update tasks
- Delete tasks
- Mark tasks as completed
- Task ownership authorization
- Validation and exception handling
- PostgreSQL database integration
- Swagger/OpenAPI documentation
- Unit testing with JUnit and Mockito

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JUnit 5
- Mockito
- Swagger / OpenAPI
- REST API
- DTO Pattern

---

## Authentication

The API uses JWT (JSON Web Tokens) for stateless authentication.

### Register

**Endpoint**

```http
POST /auth/register
```

**Request Body**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### Login

**Endpoint**

```http
POST /auth/login
```

**Request Body**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Using the Token

Include the JWT token in the Authorization header when calling protected endpoints:

```http
Authorization: Bearer <your-jwt-token>
```

---

## Swagger Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

It can be used to explore and test all API endpoints directly from the browser.

---

## Project Structure

The application follows a layered architecture:

```text
src
├── controller
├── service
├── repository
├── security
├── dto
├── mapper
├── exception
└── model
```

### Layers

- **Controller Layer** – Handles HTTP requests and responses.
- **Service Layer** – Contains business logic.
- **Repository Layer** – Handles database operations.
- **Security Layer** – JWT authentication and authorization.
- **DTOs** – Request and response objects.
- **Mapper** – Converts entities to DTOs and vice versa.
- **Exception Handling** – Global exception management.

---

## Running the Application

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE tasks;
```

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tasks
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|----------|----------|----------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT token |

### Tasks

| Method | Endpoint | Description |
|----------|----------|----------|
| GET | `/tasks` | Get all tasks for the authenticated user |
| GET | `/tasks/{id}` | Get task by ID |
| POST | `/tasks` | Create task |
| PUT | `/tasks/{id}` | Update task |
| PATCH | `/tasks/{id}/complete` | Mark task as completed |
| DELETE | `/tasks/{id}` | Delete task |

---

## Security

Each task belongs to a specific user.

Users can only:

- View their own tasks
- Update their own tasks
- Delete their own tasks

Attempts to access another user's tasks are rejected.

The API uses:

- JWT Authentication
- BCrypt Password Hashing
- Spring Security
- User-based Authorization

---

## Testing

The project includes unit tests covering:

- Service layer business logic
- Task creation
- Task retrieval
- Task updates
- Task deletion
- Exception handling
- Task ownership rules

### Run Tests

```bash
mvn test
```

---

## Future Improvements

- Pagination and sorting
- Docker support
- Integration tests
- Refresh tokens
- User roles (ADMIN / USER)
- Deployment to cloud platform

---

## Learning Goals

This project helped me practice:

- Building RESTful APIs
- Spring Boot fundamentals
- Spring Security
- JWT Authentication
- Authorization and resource ownership
- DTOs and mapping
- Exception handling
- Validation
- PostgreSQL integration
- Unit testing
- Clean project structure

---

## Author

**Robert Zdeb**

Full-Stack Developer

- GitHub: https://github.com/ivanlougan
- LinkedIn: https://www.linkedin.com/in/robert-zdeb/

Built as a backend portfolio project to demonstrate modern Java and Spring Boot development practices.