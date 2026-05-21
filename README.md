# Task Manager API

A RESTful Task Manager API built with Java and Spring Boot.

This project was created to strengthen my backend development skills and improve my understanding of REST APIs, layered architecture, database integration, and clean code practices.

## Features

* Create tasks
* Get all tasks
* Get task by ID
* Update tasks
* Delete tasks
* Mark tasks as completed
* Validation and exception handling
* PostgreSQL database integration
* Swagger/OpenAPI documentation

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Hibernate
* REST API
* DTO Pattern

## Swagger Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

It can be used to explore and test all API endpoints directly from the browser.

## Project Structure

The application follows a layered architecture:

* Controller layer
* Service layer
* Repository layer
* DTOs
* Mapper
* Exception handling

## Running the Application

### Prerequisites

* Java 17+
* Maven
* PostgreSQL

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

## API Endpoints

| Method | Endpoint             | Description            |
| ------ | -------------------- | ---------------------- |
| GET    | /tasks               | Get all tasks          |
| GET    | /tasks/{id}          | Get task by ID         |
| POST   | /tasks               | Create task            |
| PUT    | /tasks/{id}          | Update task            |
| PATCH  | /tasks/{id}/complete | Mark task as completed |
| DELETE | /tasks/{id}          | Delete task            |

## Example Request

### Create Task

POST `/tasks`

```json
{
  "title": "Learn Spring Boot"
}
```

## Future Improvements

* Pagination and sorting
* JWT authentication
* Docker support
* Unit and integration tests

## Learning Goals

This project helped me practice:

* Building RESTful APIs
* Spring Boot fundamentals
* DTOs and mapping
* Exception handling
* Validation
* PostgreSQL integration
* Clean project structure
